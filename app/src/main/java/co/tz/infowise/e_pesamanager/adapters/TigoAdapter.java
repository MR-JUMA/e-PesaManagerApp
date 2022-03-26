package co.tz.infowise.e_pesamanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import co.tz.infowise.e_pesamanager.R;
import co.tz.infowise.e_pesamanager.models.TigoTransactions;
import co.tz.infowise.e_pesamanager.utils.JSONUtil;

public class TigoAdapter extends RecyclerView.Adapter<TigoAdapter.TigoHolder> {
    Context context;
    JSONUtil jsonUtil = new JSONUtil();
    NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
    private ArrayList<TigoTransactions> dataSet;
    private OnItemClickListener mListener;

    public TigoAdapter(Context ct, ArrayList<TigoTransactions> dataSet) {
        this.context = ct;
        this.dataSet = dataSet;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TigoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transactions_card_view, parent, false);
        return new TigoHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull TigoHolder holder, int position) {
        holder.customerNumber.setText(dataSet.get(position).getPhoneNumber().toString() + "( " + dataSet.get(position).getPhoneNumber() + " )");
        holder.amount.setText(format.format(Double.parseDouble(dataSet.get(position).getAmount())));
        holder.status.setText(dataSet.get(position).getStatus());
        holder.transactionType.setText(dataSet.get(position).getTransactionType());
        holder.id.setText(dataSet.get(position).getId().toString());
//        holder.date.setText((CharSequence) new Date());

    }

    @Override
    public void onViewRecycled(@NonNull TigoHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (dataSet != null) {
            size = dataSet.size();
        }
        return size;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeliveryButtonClick(int position);

        void onAcceptOrderClick(int position);
    }

    public class TigoHolder extends RecyclerView.ViewHolder {
        TextView customerNumber, id, transactionType, date, status,amount;
        ConstraintLayout mainLayout;

        public TigoHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            //int position = getAdapterPosition();
            customerNumber = itemView.findViewById(R.id.phoneNumber);
            id = itemView.findViewById(R.id.id);
            transactionType = itemView.findViewById(R.id.type);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
            amount=itemView.findViewById(R.id.amount);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
