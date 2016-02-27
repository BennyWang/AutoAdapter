# AutoAdapter
A library for for simplifying adapter creation

## Content

### Same layout for each row

```java
String[] names = new String[] {"Jason", "Benny", "World"};

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

ViewCreator viewCreator = new ViewCreator(R.layout.list_item, new Factory<ViewHolder>() {
    ViewHolder create() {
        return new ViewHolder();
    }
});

listView.setAdapter(new AutoListAdapter<String>(names, viewCreator);

```