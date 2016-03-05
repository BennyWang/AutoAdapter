# AutoAdapter
A library for for simplifying adapter creation

## Content

### Single Layout

```java
String[] names = new String[] {"Jason", "Benny", "World"};

class ViewHolder implements IViewHolder<String> {
    @InjectView(R.id.text)
    TextView text;

    public void bind(View view) {
        ButterKnife.inject(this, view);
    }

    public void update(String data) {
        text.setText("Hello " + data);
    }
}

listView.setAdapter(new AutoListAdapter<String>(names, new ViewCreator(R.layout.list_item, ::ViewHolder));

```

### Multiple Layout

```java

class ViewHolder1 implements IViewHolder<Stock> {
    // xxxxxxx
}

class ViewHolder2 implements IViewHolder<Stock> {
    // bla bla bla
}

ViewCreatorCollection collection = new ViewCreatorCollection()
collection.add((data, position, itemCount) -> position == 1, new ViewCreator(R.layout.list_item_1, ::ViewHolder1));

ViewCreatorCollection collection = new ViewCreatorCollection()
collection.add((data, position, itemCount) -> position == 2, new ViewCreator(R.layout.list_item_2, ::ViewHolder2));

listView.setAdapter(new AutoListAdapter<Stock>(stocks, collection);

```

### Paging ListView

```java

AdapterPagingListener<Stock> pagingListener = new AdapterPagingListener<Stock>() {
    void onLoadPage(AdapterPagingCompleteHandler receiver, Stock previous, int position) {
        // bla bla bla
    }
}

ViewCreatorCollection collection = new ViewCreatorCollection()
collection.add((data, position, itemCount) -> position == 1, new ViewCreator(R.layout.list_item_1, ::ViewHolder1));

ViewCreatorCollection collection = new ViewCreatorCollection()
collection.add((data, position, itemCount) -> position == itemCount - 1 && data == null, new ViewCreator(R.layout.list_item_loading, ::LoadingViewHolder));

AutoListPagingAdapter pagingAdapter = new new AutoListPagingAdapter<Stock>(stocks, collection)
pagingListener.setPagingListener(pagingListener);
listView.setAdapter(new AutoListPagingAdapter<Stock>(pagingAdapter);

```

### Using with Gradle

```gradle
dependencies {
    compile 'com.benny.library:autoadapter:0.1.1'
}
```

### Discussion

QQ Group: 516157585