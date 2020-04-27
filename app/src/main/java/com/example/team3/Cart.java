package com.example.team3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team3.mode.CartItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Cart extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView cartTitle;
    private TextView subtotal;
    private TextView tax;
    private TextView shipping;
    private TextView total;
    private ListView list;
    private Button calculateBtn, chkoutBtn;
    private CustomAdapter cart;
    private ArrayList <CartItem> cartItems;
    private int cartitem;
    private static final String tag ="cart" ;

    private double DEFAULT_SHIPPING = 5.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cartTitle = (TextView) findViewById(R.id.cartTitle);
        subtotal = (TextView) findViewById(R.id.subtotal);
        tax = (TextView) findViewById(R.id.tax);
        shipping = (TextView) findViewById(R.id.shipping);
        total = (TextView) findViewById(R.id.total);
        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        chkoutBtn = (Button) findViewById(R.id.chkoutBtn);


        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //Set Listener for button click
        chkoutBtn.setOnClickListener (this);
        calculateBtn.setOnClickListener(this);

        //Create list of items in cart
        cartItems = new ArrayList <CartItem> ();

        //Identify list and connect listener
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);

        //Connect to custom adapter and custom items list layout
        cart = new CustomAdapter(this,R.layout.item, cartItems);
        list.setAdapter(cart);

        loadData();
    }

    private void loadData() {
        /*
        Don't know where are the products data. If we select the products from other pages and then checks out
        it suggests to use "SharedPreferences + json" to pass the cart info
        Below is the demo of reading and passing the cart data*/

        SharedPreferences sp = getSharedPreferences("data", MODE_WORLD_READABLE);
        String carsStr = sp.getString("cart", null);
        if (carsStr == null) {
            Toast.makeText(this, "No item in cart", Toast.LENGTH_SHORT).show();
            return;
        }
        try {

            JSONArray arr = new JSONArray(carsStr);
            for (int i = 0; i < arr.length(); i++) {
                CartItem item = new CartItem();
                JSONObject obj = arr.getJSONObject(i);
                item.setGoodsName(obj.getString("goodsName"));
                item.setPrice(obj.getDouble("price"));
                item.setBuyNum(obj.getInt("buyNum"));
                cartItems.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Fake data for testing
        CartItem item = new CartItem();
        item.setGoodsName("goods 1");
        item.setPrice(11);
        item.setBuyNum(1);
        cartItems.add(item);

        item = new CartItem();
        item.setGoodsName("goods 2");
        item.setPrice(22);
        item.setBuyNum(2);
        cartItems.add(item);

        item = new CartItem();
        item.setGoodsName("goods 3");
        item.setPrice(33);
        item.setBuyNum(3);
        cartItems.add(item);
        cart.notifyDataSetChanged();
    }

    //CustomAdapter extends the ArrayAdapter with a Get View to allow the listview to display the position of the row as items get added, deleted and updated.
    class CustomAdapter extends ArrayAdapter<CartItem>
    {   Context context;
        ArrayList<CartItem> cartItems;
        CustomAdapter(Context c, int item, ArrayList<CartItem> items)
        {   super(c, R.layout.item, items);
            this.context = c;
            this.cartItems=items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item, null);
            }
            TextView tvGoodsName = convertView.findViewById(R.id.goodsName);
            TextView tvPrice = convertView.findViewById(R.id.price);
            TextView tvNum = convertView.findViewById(R.id.num);
            CartItem item = getItem(position);
            tvGoodsName.setText(item.getGoodsName());
            tvPrice.setText(item.getPrice() + "");
            tvNum.setText(item.getBuyNum() + "");
            return convertView;
        }
    }

    //Checkout Button is clicked - opens Checkout Page
    @Override
    public void onClick(View v) {
        Log.i(tag, "onClick invoked.");
        switch (v.getId()) {
            case R.id.calculateBtn:
                double dsubTotal = 0;
                for (CartItem item: cartItems) {
                    dsubTotal += item.getPrice() * item.getBuyNum();
                }
                double dtax = dsubTotal * 0.0625;
                double dshipping = DEFAULT_SHIPPING;

                subtotal.setText(dsubTotal + "");
                tax.setText(dtax + "");
                shipping.setText (dshipping + "");
                total.setText((dsubTotal + dtax + dshipping) + "");
                break;
            case R.id.chkoutBtn:
                Intent i2 = new Intent(this, Checkout.class);
                startActivity(i2);
                Log.i(tag, "onClick complete.");
                break;
        }
    }

    //Called when item in cart is clicked and displays selected item in editText (insert) field
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        CartItem text = cartItems.get(position);
        //insert.setText(text);
        Log.i(tag, "Item selected");
        cartitem = position;
    }

    //Insert Option Menu to Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //When Menu Item is Clicked - determines what to do
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i1 = new Intent(this, Home.class);
                startActivity(i1);
                return true;

            case R.id.products:
                Intent i2 = new Intent(this, Product.class);
                startActivity(i2);
                return true;

            case R.id.dye:
                Intent i3 = new Intent(this, Dye.class);
                startActivity(i3);
                return true;

            case R.id.cart:
                Intent i4 = new Intent(this, Cart.class);
                startActivity(i4);
                return true;

            case R.id.contact:
                Intent i5 = new Intent(this, Contact.class);
                startActivity(i5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}