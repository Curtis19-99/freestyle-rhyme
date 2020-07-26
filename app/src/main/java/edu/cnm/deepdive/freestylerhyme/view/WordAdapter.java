package edu.cnm.deepdive.freestylerhyme.view;

//import edu.cnm.deepdive.freestylerhyme.view.ResultAdapter.Holder;
//import edu.cnm.deepdive.freestylerhyme.view.ResultAdapter.OnClickListener;

//public class WordAdapter extends RecyclerView.Adapter<Holder> {
//
//  private final Context context;
//  private final List<WordWithResult> words;
//  private final OnClickListener clickListener;
//
//  public WordAdapter(Context context, List<WordWithResult> words,OnClickListener clickListener) {
//    super();
//    this.context = context;
//    this.words = words;
//    this.clickListener = clickListener;
//  }
//
//  @NonNull
//  @Override
//  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    View view = null;
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
//    return words.size();
//  }
//
//  class Holder extends RecyclerView.ViewHolder {
//    private final View itemView;
//  }
//
//  public Holder(@NonNull View itemView) {
//    super(itemView);
//    this.itemView = itemView;
//  }
//
//  private void bind(int position) {
//    WordWithResult item = words.get(position);
//    itemView.setOnClickListener((v) -> clickListener.onClick(v, getAdapterPosition(), item));
//  }
//
//  public interface OnClickListener {
//    void onClick(View v, int position, WordWithResult word);
//  }
//
//}
