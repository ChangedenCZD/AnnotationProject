package net.changeden.annotationproject.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.changeden.annotationproject.R;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnResize;
import net.changeden.annotationproject.annotations.manager.AnnotationManager;
import net.changeden.annotationproject.annotations.manager.support.AnnotationViewGetterInterface;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_main,null);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnnotationManager.getInstance().init(this);

        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Adapter());
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(MainFragment.class.getName()+" ===== onResume");
    }

    @OnResize(id = R.id.text , width = 300)
    TextView textView;

    private class Adapter extends RecyclerView.Adapter<VH>{

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(getActivity()).inflate(R.layout.item_recyclerview_demo,null));
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.textView.setText("测试一下");
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    private class VH extends RecyclerView.ViewHolder implements AnnotationViewGetterInterface {
        View itemView;
        @OnResize(id = R.id.textView,width = 400,height = 100)
        TextView textView;
        @OnResize(id = R.id.textView2,width = 200,height = 50)
        TextView textView2;
        public VH(View itemView) {
            super(itemView);
            this.itemView=itemView;
            AnnotationManager.getInstance().init(getActivity(),this);
        }

        @Override
        public View getView() {
            return itemView;
        }
    }
}
