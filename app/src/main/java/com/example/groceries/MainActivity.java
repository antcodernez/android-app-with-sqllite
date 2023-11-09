package com.example.groceries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtBarcode;
    private EditText txtDescription;
    private EditText txtBrand;
    private EditText txtCost;
    private EditText txtPrice;
    private EditText txtStock;
    private Button btnSave;
    private ListView lvProducts;
    private Product product;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBarcode=findViewById(R.id.txt_barcode);
        txtDescription=findViewById(R.id.txt_description);
        txtBrand=findViewById(R.id.txt_brand);
        txtCost=findViewById(R.id.txt_cost);
        txtPrice=findViewById(R.id.txt_price);
        txtStock=findViewById(R.id.txt_stock);
        btnSave=findViewById(R.id.btn_save);
        lvProducts=findViewById(R.id.lv_products);
        productDAO=new ProductDAO(this);
        updateList();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product= new Product();
                product.setBarcode(txtBarcode.getText().toString());
                product.setDescription(txtDescription.getText().toString());
                product.setBrand(txtBrand.getText().toString());
                product.setCost(Float.parseFloat(txtCost.getText().toString()));
                product.setPrice(Float.parseFloat(txtPrice.getText().toString()));
                product.setStock(Integer.parseInt(txtStock.getText().toString()));

                if(productDAO.insertProduct(product)!= -1) {
                    Toast.makeText(MainActivity.this, "Producto insertado con éxito!", Toast.LENGTH_LONG).show();
                    updateList();
                    clearFields();
                }
                else
                    Toast.makeText(MainActivity.this, "Servicio no disponible",Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void updateList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                productDAO.getAllBarcodes()
        );
        lvProducts.setAdapter(adapter);
    }

    protected void clearFields()
    {
        txtBarcode.setText("");
        txtDescription.setText("");
        txtBrand.setText("");
        txtCost.setText("");
        txtPrice.setText("");
        txtStock.setText("");
    }
}