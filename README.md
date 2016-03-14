# AutoAdapter
A library for for simplifying adapter creation

## Content

### Single Layout

```java

class ViewHolder implements IViewHolder<DataType> {
    // bla bla
}

listView.setAdapter(new AutoListAdapter(items, new ViewCreator(R.layout.list_item, ::ViewHolder));

```

### Multiple Layout

```java

class ViewHolder1 implements IViewHolder<DataType> {
    // bla bla
}

class ViewHolder2 implements IViewHolder<DataType> {
    // bla bla bla
}

ViewCreatorCollection<DataType> collection = new ViewCreatorCollection.Builder<DataType>()
    .addFilter(data, position, itemCount) -> position == 1, R.layout.list_item_1, ::ViewHolder1)
    .addFilter((data, position, itemCount) -> position == 2, R.layout.list_item_2, ::ViewHolder2).build();
    
listView.setAdapter(new AutoListAdapter(stocks, collection);

```

### Paging ListView

```java

class ViewHolder implements IViewHolder<DataType> {
    // bla bla
}

AdapterPagingListener<DataType> pagingListener = new AdapterPagingListener<DataType>() {
    void onLoadPage(AdapterPagingCompleteHandler receiver, DataType previous, int position) {
        // bla bla bla
    }
}

ViewCreatorCollection collection = new ViewCreatorCollection.Builder<DataType>()
    .loadingResId(R.layout.list_item_loading)
    .addFilter(R.layout.list_item, ::ViewHolder)
    .build();

listView.setAdapter(new AutoListPagingAdapter<DataType>(stocks, collection, pagingListener));

```

### Using with Gradle

```gradle
dependencies {
    compile 'com.benny.library:autoadapter:0.1.3'
}
```

### Discussion

QQ Group: 516157585
