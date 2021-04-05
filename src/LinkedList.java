// Linked list class
public class LinkedList {
    Node head;    //Head of list

    /* Inserts a new Node at front of the list. */
    public void push(Employee new_data) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    // Method to insert a new node
    public void insert(Employee new_data)
    {
        // Create a new node with given data
        Node new_node = new Node(new_data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (head == null) {
            head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }
    }

    // Checks whether the value x is present
    // in linked list
    public int searchNode(int data) {
        int i = 0;
        Node current = head;    //Initialize current
        while (current != null) {
            if (current.data.getPhone() == data)
                return i;    //data found
            current = current.next;
            i++;
        }
        return -1;    //data not found
    }

    public String printLinkedList(boolean type){
        String s;
        if (type)
            s = "Null";
        else
            s = "null";
        Node current = head;
        if (current != null){
            s = "" + current.data.getPhone();
            current = current.next;
        }
        while (current != null) {
            s += "---->" + current.data.getPhone();
            current = current.next;
        }
        return s;
    }

    public boolean isFree(){
        if (head == null)
            return true;
        else
            return false;
    }
}

// Node class
class Node {
    Employee data;
    Node next;

    Node(Employee d) {
        data = d;
        next = null;
    }
}