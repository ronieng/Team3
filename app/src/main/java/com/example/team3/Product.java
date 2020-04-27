package com.example.team3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Product extends AppCompatActivity {

    private String product_name;
    private String product_category;
    private String product_description;
    private int thumbnail;
    List<Product> lstProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //inputting data
        lstProd = new ArrayList<>();
        lstProd.add(new Product("Rose","Category Product", "Product Description", R.drawable.rose));
        lstProd.add(new Product("Christmas Tree","Category Product", "Product Description", R.drawable.christmas_tree));
        lstProd.add(new Product("Football","Category Product", "Product Description", R.drawable.football));
        lstProd.add(new Product("Conversation Heart","Category Product", "Product Description", R.drawable.conversation_heart));
        lstProd.add(new Product("Ginger Bread","Category Product", "Product Description", R.drawable.gingerbread));
        lstProd.add(new Product("Snow Man","Category Product", "Product Description", R.drawable.snowman));
        lstProd.add(new Product("Alien","Category Product", "Product Description", R.drawable.alien));
        lstProd.add(new Product("Lavender","Category Product", "Product Description", R.drawable.lavender));
        lstProd.add(new Product("Nag Champa","Category Product", "Product Description", R.drawable.nag_champa));
        lstProd.add(new Product("Unicorn","Category Product", "Product Description", R.drawable.unicorn));
        lstProd.add(new Product("Sweet Kisses","Category Product", "Product Description", R.drawable.sweet_kisses));
        lstProd.add(new Product("Apple","Category Product", "Product Description", R.drawable.apple));
        lstProd.add(new Product("Hello Kitty","Category Product", "Product Description", R.drawable.hello_kitty));
        lstProd.add(new Product("Lotus Blossom","Category Product", "Product Description", R.drawable.lotus_blossom));
        lstProd.add(new Product("Minions","Category Product", "Product Description", R.drawable.minions));
        lstProd.add(new Product("Owl","Category Product", "Product Description", R.drawable.owl));
        lstProd.add(new Product("Paisley","Category Product", "Product Description", R.drawable.paisley));
        lstProd.add(new Product("Pink Skull","Category Product", "Product Description", R.drawable.pink_skull));
        lstProd.add(new Product("Poop Emoji","Category Product", "Product Description", R.drawable.poop_emoji));
        lstProd.add(new Product("Rose Shaped","Category Product", "Product Description", R.drawable.rose_shaped));
        lstProd.add(new Product("Strawberry","Category Product", "Product Description", R.drawable.strawberry));

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstProd);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(myAdapter);


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
    public Product() {
    }

    public Product(String product_name, String product_category, String product_description, int thumbnail) {
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_description = product_description;
        this.thumbnail = thumbnail;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public String getProduct_description() {
        return product_description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{

    private Context mContext;
    private List<Product> mData;

    public RecyclerViewAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {

        holder.tv_prod_name.setText(mData.get(position).getProduct_name());
        holder.img_prod_thumbnail.setImageResource(mData.get(position).getThumbnail());
        // setting click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing data to the book activity
                //Intent intent = new Intent(mContext,ProductActivity.class);
                //intent.putExtra("ProductName",mData.get(position).getProduct_name());
               // intent.putExtra("Description",mData.get(position).getProduct_description());
               // intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
               // mContext.startActivity(intent); // start the activity
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView tv_prod_name;
        ImageView img_prod_thumbnail;
        CardView cardView;

        public myViewHolder(View itemView) {
            super(itemView);

            tv_prod_name = (TextView) itemView.findViewById(R.id.prod_name_id);
            img_prod_thumbnail = (ImageView) itemView.findViewById(R.id.product_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }

    }

}
