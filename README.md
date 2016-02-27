# AutoAdapter
A library for for simplifying adapter creation

## Content

### Same layout for each row

```java

class ViewHolder implements AbstractViewHolder<String> {
    @InjectView(R.id.text)
    TextView text;
    
    public void bind(View view) {
        ButterKnife.inject(this, view);
    }
    
    public void update(String data) {
        text.setText("Hello " + data);
    }
}

listView.setAdapter(new AutoListAdapter<String>(R.layout.list_item, new Factory<ViewHolder>() {
    ViewHolder create() {
        return new ViewHolder();
    }
}));

```