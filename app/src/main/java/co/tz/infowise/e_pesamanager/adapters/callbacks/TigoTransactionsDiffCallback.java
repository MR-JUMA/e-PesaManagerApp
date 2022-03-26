//package co.tz.infowise.e_pesamanager.adapters.callbacks;
//
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.DiffUtil;
//
//import java.util.ArrayList;
//
//import co.tz.infowise.e_pesamanager.models.TigoTransactions;
//
//public class TigoTransactionsDiffCallback extends DiffUtil.Callback {
//    private ArrayList<TigoTransactions> mOldList;
//    private ArrayList<TigoTransactions> mNewList;
//
//    public TigoTransactionsDiffCallback(ArrayList<TigoTransactions> oldList, ArrayList<TigoTransactions> newList) {
//        this.mOldList = oldList;
//        this.mNewList = newList;
//    }
//    @Override
//    public int getOldListSize() {
//        return mOldList.size();
//    }
//
//    @Override
//    public int getNewListSize() {
//        return mNewList.size();
//    }
//
//    @Override
//    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
//    }
//
//    @Override
//    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        TigoTransactions oldTransaction = mOldList.get(oldItemPosition);
//        TigoTransactions newTransaction = mNewList.get(newItemPosition);
//
//        return oldTransaction.getStatus().equals( newTransaction.getStatus());
//    }
//
//    @Nullable
//    @Override
//    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
//        return super.getChangePayload(oldItemPosition, newItemPosition);
//    }
//
//}
