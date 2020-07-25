package edu.cnm.deepdive.freestylerhyme.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.freestylerhyme.R;
import edu.cnm.deepdive.freestylerhyme.model.pojo.ResultWithWord;
//import edu.cnm.deepdive.freestylerhyme.view.ResultAdapter.Holder;
import java.util.List;

//public class ResultAdapter extends RecyclerView.Adapter<Holder> {
//
//  private final String unattributedWord;
//  private final List<ResultWithWord> results;
//  private final OnClickListener clickListener;
//  private final Context context;
//  private final String wordFormat;
//
//  public ResultAdapter(Context context, List<ResultWithWord> results,
//      OnClickListener clickListener) {
//    super();
//    this.context = context;
//    this.results = results;
//    unattributedWord = context.getString(R.string.unattributed_word);
//    wordFormat = context.getString(R.string.word_format);
//    this.clickListener = clickListener;
//  }
//
//  @NonNull
//  @Override
//  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    View view = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false); //TODO implement in xml layout.
//    return new Holder(view);
//  }
//
//  @Override
//  public void onBindViewHolder(@NonNull Holder holder, int position) {
//    holder.bind(position);
//  }
//
//  @Override
//  public int getItemCount() {
//    return results.size();
//  }
//
//  class Holder extends RecyclerView.ViewHolder {
//
//    private final View itemView;
//    private final TextView result;
//    private final TextView word;
//
//    public Holder(@NonNull View itemView) {
//      super(itemView);
//      this.itemView = itemView;
//      result = itemView.findViewById(R.id.result); //TODO implement layout names in xml fragments.
//      word = itemView.findViewById(R.id.word); //TODO implement layout names in xml fragments.
//    }
//
//    private void bind(int position) {
//      ResultWithWord item = results.get(position);
//      String wordName =
//          (item.getWord() != null) ? item.getWord().getName() : unattributedWord;
//      result.setText(item.getText());
//      word.setText(String.format(wordFormat, wordName));
//      itemView.setOnClickListener((v) -> clickListener.onClick(v, getAdapterPosition(), item));
//    }
//
//  }
//
//  public interface OnClickListener {
//
//    void onClick(View v, int position, ResultWithWord result);
//  }
//
//}
