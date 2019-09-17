package searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;

public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    // 符号表中的key-value对
    private int n;
    // 线性探测表的大小
    private int m;
    private Key[] keys;
    private Value[] vals;

    /**
     * 初始化一个空符号表
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        // 弱类型，数组内对象不会有任何类型检查（不推荐）
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }


    public int size() {
        return n;
    }


    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contain() is null");
        }
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }

        if (val == null) {
            delete(key);
            return;
        }
        if (n >= m / 2) {
            resize(m * 2);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }

        // 找到key的位置i
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // 删除相关的key和value
        keys[i] = null;
        vals[i] = null;

        // 在同一个集群里rehash所有的keys
        i = (i + 1) % m;
        while (keys[i] != null) {
            // 删除keys[i]和vals[i]重新插入
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
