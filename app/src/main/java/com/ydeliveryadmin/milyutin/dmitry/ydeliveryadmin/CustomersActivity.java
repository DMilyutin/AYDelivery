package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterCustomerActivity;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

public class CustomersActivity extends AppCompatActivity {

    public static final String COLLECTION_CUSTOMER_BALASHIHA = "customer_balashiha";

    private ListView customersList;
    private AdapterCustomerActivity adapterCustomerActivity;
    private Adapter adapterT;

    private ProgressBar prBarCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        customersList = findViewById(R.id.customersList);
        prBarCustomer = findViewById(R.id.prBarCustomerActivity);

        getOllDrivers();

    }


    private void getOllDrivers(){
        Query query = new Query(COLLECTION_CUSTOMER_BALASHIHA);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                setAdapter(documentInfos);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                customersList.setAdapter((ListAdapter) adapterT);
                Toast.makeText(CustomersActivity.this, "Скорее всего ошибка! Сообщи об этом!!!"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter(List<DocumentInfo> documentInfos) {
        adapterCustomerActivity = new AdapterCustomerActivity(this, documentInfos);
        customersList.setAdapter(adapterCustomerActivity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addItem :{
                viewAddCustomer();}
        }
        return true;
    }

    private void viewAddCustomer(){

        View dialog = getLayoutInflater().inflate(R.layout.dialog_add_customer, null);

        final EditText edNameCustomer = dialog.findViewById(R.id.edDialogAddCustomerName);
        final EditText edAddressCustomer = dialog.findViewById(R.id.edDialogAddCustomerAddress);
        final EditText edPhoneCustomer = dialog.findViewById(R.id.edDialogAddCustomerPhone);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                prBarCustomer.setVisibility(ProgressBar.VISIBLE);
                String name = edNameCustomer.getText().toString();
                String address = edAddressCustomer.getText().toString();
                String phone = edPhoneCustomer.getText().toString();
                addCustomer(name, address, phone);
            }
        }).setView(dialog).create().show();

    }

    private void addCustomer(final String name, final String address, final String phone) {
        Document doc = new Document(COLLECTION_CUSTOMER_BALASHIHA);
        doc.setField("nameCustomer", name);
        doc.setField("addressCustomer", address);
        doc.setField("phoneCustomer", phone);
        doc.setField("loginCustomer", phone);
        doc.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                findDoc(name, address, phone);
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                Toast.makeText(CustomersActivity.this, "Ошибка: " + errorMessage
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findDoc(String name, String address, final String phone) {
        Query query = new Query(COLLECTION_CUSTOMER_BALASHIHA)
                .equalTo("nameCustomer", name)
                .equalTo("addressCustomer", address)
                .equalTo("phoneCustomer", phone);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                String id = documentInfos.get(0).getId();
                registerCustomer(id, phone);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Toast.makeText(CustomersActivity.this, "Ошибка: " + errorMessage
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registerCustomer(final String id, final String phone) {
        String pass = "111111";
        Document doc = new Document("users");
        doc.setField("idCustomer", id);
        doc.setField("phone", phone);
        doc.setField("Admin", false);

        User user = new User();
        user.register("Customer", phone+"@mail.ru", pass, doc.getDocumentContent(),
                new CallbackRegisterUser() {
                    @Override
                    public void onRegisterSucceed() {

                        updateUserTable(id, phone);
                    }

                    @Override
                    public void onRegisterFailed(String errorCode, String errorMessage) {
                        Toast.makeText(CustomersActivity.this, "Ошибка: " + errorMessage
                                ,Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void updateUserTable(String id, final String phone) {
        final String pass = "1";
        Query query = new Query("users")
                .equalTo("idCustomer", id)
                .equalTo("phone",phone);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                String userId = documentInfos.get(0).getId();
                final Document document = new Document("users");
                document.getDocumentById(userId, new CallbackGetDocumentById() {
                    @Override
                    public void onDocumentFound(DocumentInfo documentInfo) {
                        document.updateDocument()
                                .set("email", phone)
                                .set("password", pass);

                        document.saveDocument(new CallbackDocumentSaved() {
                            @Override
                            public void onDocumentSaved() {
                                prBarCustomer.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(CustomersActivity.this, "Заказчик успешно добавлен"
                                        ,Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                                prBarCustomer.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(CustomersActivity.this, "Ошибка: " + errorMessage
                                        ,Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        Toast.makeText(CustomersActivity.this, "Ошибка: " + errorMessage
                                ,Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Toast.makeText(CustomersActivity.this, "Ошибка: " + errorMessage
                        ,Toast.LENGTH_LONG).show();
            }
        });


    }
}
