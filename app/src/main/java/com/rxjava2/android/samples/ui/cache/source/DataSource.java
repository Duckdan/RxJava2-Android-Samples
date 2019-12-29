package com.rxjava2.android.samples.ui.cache.source;

import com.rxjava2.android.samples.ui.cache.model.Data;

import io.reactivex.Observable;

/**
 * The DataSource to handle 3 data sources - memory, disk, network
 */
public class DataSource {

    private final MemoryDataSource memoryDataSource;
    private final DiskDataSource diskDataSource;
    private final NetworkDataSource networkDataSource;

    public DataSource(MemoryDataSource memoryDataSource,
                      DiskDataSource diskDataSource,
                      NetworkDataSource networkDataSource) {
        this.memoryDataSource = memoryDataSource;
        this.diskDataSource = diskDataSource;
        this.networkDataSource = networkDataSource;
    }

    /**
     * 从内存中拿数据
     * @return
     */
    public Observable<Data> getDataFromMemory() {
        return memoryDataSource.getData();
    }

    /**
     * 从磁盘中拿数据
     * @return
     */
    public Observable<Data> getDataFromDisk() {
        return diskDataSource.getData().doOnNext(data ->
                memoryDataSource.cacheInMemory(data)
        );
    }

    /**
     * 从网络中拿数据
     * @return
     */
    public Observable<Data> getDataFromNetwork() {
        return networkDataSource.getData().doOnNext(data -> {
            diskDataSource.saveToDisk(data);
            memoryDataSource.cacheInMemory(data);
        });
    }

}
