package es.riberadeltajo.tarea6_miguelmanzanillaocaa;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import es.riberadeltajo.tarea6_miguelmanzanillaocaa.databinding.FragmentAudioBinding;

import java.util.List;


public class MyaudioRecyclerViewAdapter extends RecyclerView.Adapter<MyaudioRecyclerViewAdapter.ViewHolder> {

    private final List<Audio> mValues;
    private int cont=0;

    public MyaudioRecyclerViewAdapter(List<Audio> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAudioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.titulo.setText(mValues.get(position).getNombre());
        holder.desc.setText(mValues.get(position).getDescripcion());
        holder.img.setImageBitmap(mValues.get(position).getImg());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mValues.get(holder.getAbsoluteAdapterPosition()).getTipo()==0){
                    //AUDIO
                    if (cont==0){
                        String nombreRecurso = mValues.get(holder.getAbsoluteAdapterPosition()).getUri();
                        int recurso = v.getContext().getResources().getIdentifier(nombreRecurso, "raw", v.getContext().getPackageName());
                        if (recurso==0){
                            Toast.makeText(v.getContext(), "NO EXISTE RECURSO DE CANCION",Toast.LENGTH_LONG).show();
                        }else{
                            ListaAudios.mp = MediaPlayer.create(v.getContext(),recurso);
                            ListaAudios.mp.start();
                            MainActivity.mc.show(0);
                        }
                        cont++;
                    }else{
                        ListaAudios.mp.pause();
                        String nombreRecurso = mValues.get(holder.getAbsoluteAdapterPosition()).getUri();
                        int recurso = v.getContext().getResources().getIdentifier(nombreRecurso, "raw", v.getContext().getPackageName());
                        if (recurso==0){
                            Toast.makeText(v.getContext(), "NO EXISTE RECURSO DE CANCION",Toast.LENGTH_LONG).show();
                        }else{
                            ListaAudios.mp = MediaPlayer.create(v.getContext(),recurso);
                            ListaAudios.mp.start();
                            MainActivity.mc.show(0);
                        }

                    }


                } else if (mValues.get(holder.getAbsoluteAdapterPosition()).getTipo()==1) {
                    //VIDEO
                    CargarVideo.version=1;
                    CargarVideo.recurso=mValues.get(holder.getAbsoluteAdapterPosition()).getUri();
                    Intent i=new Intent(v.getContext(), CargarVideo.class);
                    v.getContext().startActivity(i);
                }else if (mValues.get(holder.getAbsoluteAdapterPosition()).getTipo()==2){
                    //STREAMING
                    Intent i=new Intent(v.getContext(), CargarVideo.class);
                    CargarVideo.recurso=mValues.get(holder.getAbsoluteAdapterPosition()).getUri();
                    CargarVideo.version=2;
                    v.getContext().startActivity(i);

                }
            }
        });

        if (mValues.get(position).getTipo()==0){
            holder.icon.setImageResource(R.drawable.music);
        } else if (mValues.get(position).getTipo()==1) {
            holder.icon.setImageResource(R.drawable.vidio);
        }else if (mValues.get(position).getTipo()==2){
            holder.icon.setImageResource(R.drawable.stream);
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titulo;
        public final TextView desc;
        public final ImageView img;
        public final  ImageView icon;
        public final ImageButton btn;
        public Audio mItem;

        public ViewHolder(FragmentAudioBinding binding) {
            super(binding.getRoot());
            titulo=binding.titulo;
            desc=binding.desc;
            img=binding.imageView3;
            icon=binding.icon;
            btn=binding.imageButton3;

        }


    }
}