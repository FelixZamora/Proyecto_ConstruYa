package co.edu.ue.practica_login_api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.edu.ue.practica_login_api.R;
import co.edu.ue.practica_login_api.model.Productos;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private List<Productos> productos;
    private Context context;

    public ProductosAdapter(List<Productos> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_nombre.setText(productos.get(position).getUse_nombre());
        Glide.with(context).load(productos.get(position).getUse_imagen()).into(holder.iv_imagen);
        holder.tv_precio.setText("$ " + productos.get(position).getUse_precio());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_imagen;
        private TextView tv_nombre;
        private TextView tv_precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_imagen = itemView.findViewById(R.id.iv_producto);
            this.tv_nombre = itemView.findViewById(R.id.tv_nombre);
            this.tv_precio = itemView.findViewById(R.id.tvPrecioProducto);

        }
    }
}
