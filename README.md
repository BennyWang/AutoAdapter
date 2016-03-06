# AutoAdapter
A library for for simplifying adapter creation

## Content

### Single Layout

```java

class ViewHolder implements IViewHolder<String> {
    // bla bla
}

listView.setAdapter(new AutoListAdapter(data, new ViewCreator(R.layout.list_item, ::ViewHolder));

```

### Multiple Layout

```java

class ViewHolder1 implements IViewHolder<Stock> {
    // bla bla
}

class ViewHolder2 implements IViewHolder<Stock> {
    // bla bla bla
}

ViewCreatorCollection<DataType> collection = new ViewCreatorCollection.Builder<DataType>()
    .addFilter(data, position, itemCount) -> position == 1, R.layout.list_item_1, ::ViewHolder1)
    .addFilter((data, position, itemCount) -> position == 2, R.layout.list_item_2, ::ViewHolder2).build();
    
listView.setAdapter(new AutoListAdapter(stocks, collection);

```

### Paging ListView

```java

AdapterPagingListener<DataType> pagingListener = new AdapterPagingListener<Stock>() {
    void onLoadPage(AdapterPagingCompleteHandler receiver, Stock previous, int position) {
        // bla bla bla
    }
}

ViewCreatorCollection collection = new ViewCreatorCollection.Builder<DataType>().loadingResId(R.layout.list_item_1)
    .addFilter((data, position, itemCount) -> data != null, R.layout.list_item, ::LoadingViewHolder).build();

listView.setAdapter(new AutoListPagingAdapter<DataType>(stocks, collection, pagingListener));

```

### Using with Gradle

```gradle
dependencies {
    compile 'com.benny.library:autoadapter:0.1.1'
}
```

### Discussion

QQ Group: 516157585
