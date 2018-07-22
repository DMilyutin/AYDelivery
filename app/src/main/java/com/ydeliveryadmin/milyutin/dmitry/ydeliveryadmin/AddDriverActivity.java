package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

public class AddDriverActivity extends AppCompatActivity {

    EditText edAddDriverActivityName;
    EditText edAddDriverActivityLastName;
    EditText edAddDriverActivityPhone;
    EditText edAddDriverActivityCar;
    EditText edAddDriverActivityCarNumber;
    EditText edAddDriverActivityPasportData;

    Button btAddCustomerActivityAdd;

    ProgressBar pbAddDriverActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        edAddDriverActivityName = findViewById(R.id.edAddDriverActivityName);
        edAddDriverActivityLastName = findViewById(R.id.edAddDriverActivityLastName);
        edAddDriverActivityPhone = findViewById(R.id.edAddDriverActivityPhone);
        edAddDriverActivityCar = findViewById(R.id.edAddDriverActivityCar);
        edAddDriverActivityCarNumber = findViewById(R.id.edAddDriverActivityCarNumber);
        edAddDriverActivityPasportData = findViewById(R.id.edAddDriverActivityPasportData);
        pbAddDriverActivity = findViewById(R.id.pbAddDriverActivity);


        btAddCustomerActivityAdd = findViewById(R.id.btAddDriverActivityAdd);
        btAddCustomerActivityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbAddDriverActivity.setVisibility(ProgressBar.VISIBLE);
                addDriver();
            }
        });
    }

    private void addDriver(){
        String name = edAddDriverActivityName.getText().toString();
        String lastName = edAddDriverActivityLastName.getText().toString();
        final String phone =edAddDriverActivityPhone.getText().toString();
        String car =edAddDriverActivityCar.getText().toString();
        String carNumber =edAddDriverActivityCarNumber.getText().toString();
        String passport =edAddDriverActivityPasportData.getText().toString();

        Document document = new Document("drivers_balashiha");
        document.setField("nameDriver", name);
        document.setField("lastNameDriver", lastName);
        document.setField("phoneNumber", phone);
        document.setField("carDriver", car);
        document.setField("carNumber", carNumber);
        document.setField("passportDriver", passport);
        document.setField("balanceDriver", 0);
        document.setField("statusWork", false);

        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                registrDriver(phone);
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                Toast.makeText(AddDriverActivity.this, "Ошибка: " + errorMessage
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registrDriver(final String phone) {
        String pass = "111111";
        Document document = new Document("users");
        document.setField("Admin", false);
        document.setField("phone", phone);

        User user = new User();
        user.register("Driver", phone+"@mail.ru", pass, document.getDocumentContent(),
                new CallbackRegisterUser() {
                    @Override
                    public void onRegisterSucceed() {

                        updateUserTable(phone);
                    }

                    @Override
                    public void onRegisterFailed(String errorCode, String errorMessage) {
                        Toast.makeText(AddDriverActivity.this, "Ошибка: " + errorMessage
                                ,Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void updateUserTable(final String phone) {
        final String pass = "1";
        Query query = new Query("users")
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
                                pbAddDriverActivity.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(AddDriverActivity.this, "Заказчик успешно добавлен"
                                        ,Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }

                            @Override
                            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                                pbAddDriverActivity.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(AddDriverActivity.this, "Ошибка: " + errorMessage
                                        ,Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        Toast.makeText(AddDriverActivity.this, "Ошибка: " + errorMessage
                                ,Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Toast.makeText(AddDriverActivity.this, "Ошибка: " + errorMessage
                        ,Toast.LENGTH_LONG).show();
            }
        });


    }
}
