class BatchInsert implements AutoCloseable {

    private int batchSize = 0;
    private final int batchLimit;

    public BatchInsert(int batchLimit) {
        this.batchLimit = batchLimit;
    }

    public void insert(String a, String b, String c) {
        if (++batchSize >= batchLimit) {
            sendBatch();
        }
    }

    public void sendBatch() {
        System.out.format("Send batch with %d records%n", batchSize);
        batchSize = 0;
    }

    @Override
    public void close() {
        if (batchSize != 0) {
            sendBatch();
        }
    }
}