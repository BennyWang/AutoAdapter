# AutoAdapter
A library for for simplifying adapter creation, support List, RecyclerView, ViewPager.

## Content

### ViewHolder

```java

public class DataGetter<T> {
    final public T previous;
    final public T next;
    final public T data;

    public DataGetter(T previous, T data, T next) {
        this.previous = previous;
        this.next = next;
        this.data = data;
    }
}

public interface IViewHolder<T> {

    // create view and bind to this view holder
    void bind(View view);

    // position: item position in list
    // getter: access current item and previous and next, this is useful when decide show or hide some view depened on previous item or next item or all
    void onDataChange(DataGetter<T> getter, int position);
}

```

### ViewCreator

```java

public interface IViewCreator<T> {
    // call in getView
    View view(ViewGroup container);
    // call in getItemViewType
    int viewTypeFor(T data, int position, int itemCount);
    // call in getViewTypeCount
    int viewTypeCount();
}

```

### Single Layout

```java
// ViewCreator just a implement of IViewCreator, accept layout id and view holder factory
new AutoListAdapter(items, new ViewCreator(R.layout.list_item, () -> new ViewHolder());

```

### Multiple Layout

```java
// ViewCreatorCollection just another implement of IViewCreator, it create different view depends on values of item and position and itemCount.
// It useful when getViewTypeCount > 1
ViewCreatorCollection<DataType> collection = new ViewCreatorCollection.Builder<DataType>()
    .addFilter((data, position, itemCount) -> position == 1, R.layout.list_item_1, ViewHolder1::new)
    .addFilter((data, position, itemCount) -> position == 2, R.layout.list_item_2, ViewHolder2::new).build();
new AutoListAdapter(items, collection);

```

### Paging ListView

```java

ViewCreatorCollection collection = new ViewCreatorCollection.Builder<DataType>()
    .loadingResId(R.layout.list_item_loading)
    .addFilter((data, position, itemCount) -> data != null, R.layout.list_item, ViewHolder::new)
    .build();

// pagingListener will be called when arrive the last position in ListView
new AutoListPagingAdapter<DataType>(stocks, collection, pagingListener);

```

### Using with Gradle

```gradle
dependencies {
    compile 'com.benny.library:autoadapter:0.2.8'
}
```

### Discussion

QQ Group: 516157585
