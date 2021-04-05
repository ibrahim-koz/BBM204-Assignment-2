public class MyHashTable {
    LinkedList[] hashTable;
    int tableSize;
    int employeeNumber;

    public int getTableSize() {
        return tableSize;
    }

    double loadFactor;

    public MyHashTable(int employeeNumber, double loadFactor) {
        this.employeeNumber = employeeNumber;
        this.loadFactor = loadFactor;
        this.tableSize = (int) (employeeNumber / loadFactor);
        hashTable = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            hashTable[i] = new LinkedList();
    }


    int hash1(int key) {
        return key % tableSize;
    }

    int hash2(int key) {
        return 1 + (key % (tableSize - 1));
    }

    public int doubleHashFunction(int key, int i) {
        return (hash1(key) + i * hash2(key)) % tableSize;
    }


    public void separateChaining(Employee newEmployee) {
        int theKey = hash1(newEmployee.getPhone());
        hashTable[theKey].insert(newEmployee);
        return;
    }


    public void linearProbing(Employee newEmployee) {
        int theKey = hash1(newEmployee.getPhone());
        while (true) {
            if (hashTable[theKey].isFree()) {
                hashTable[theKey].insert(newEmployee);
                break;
            } else
                theKey += 1;
            theKey %= tableSize;
        }
        return;
    }

    public void doubleHashing(Employee newEmployee) {
        int theKey = hash1(newEmployee.getPhone());
        int i = 1;
        if (!(hashTable[theKey].isFree()))
            theKey = doubleHashFunction(newEmployee.getPhone(), i);
        while (true) {
            if (hashTable[theKey].isFree()) {
                hashTable[theKey].insert(newEmployee);
                break;
            } else{
                i++;
                theKey = doubleHashFunction(newEmployee.getPhone(), i);
            }
        }
        return;
    }

    public int sepGet(int phone) {
        int thePhone = hash1(phone);
        return hashTable[thePhone].searchNode(phone) + 1;
    }

    public int linGet(int phone) {
        int thePhone = hash1(phone);
        int s = 1;
        while (hashTable[thePhone].searchNode(phone) == -1){
            s++;
            thePhone++;
            thePhone %= tableSize;
        }
        return s;
    }

    public int doubleGet(int phone){
        int thePhone = hash1(phone);
        int s = 1;
        int i = 1;
        if (hashTable[thePhone].searchNode(phone) == -1) {
            thePhone = doubleHashFunction(phone, i);
            s++;
            while (hashTable[thePhone].searchNode(phone) == -1) {
                i++;
                thePhone = doubleHashFunction(phone, i);
            }
        }
        return s;
    }

    public LinkedList getHead(int i) {
        return hashTable[i];
    }

}
