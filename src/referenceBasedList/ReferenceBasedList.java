package referenceBasedList;

import objects.Product;

import java.text.Collator;
import java.util.Locale;


public class ReferenceBasedList implements ListInterface {
    private ListNode head;
    private ListNode tail;
    int numItems;

    public ReferenceBasedList() {
        head = null;
        tail = null;
        numItems = 0;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int size() {
        return numItems;
    }

    public void showAll() { //this should not exist (bad practice, just for testing)
        int count = 1;
        for (ListNode curr = head; curr != null; curr = curr.getNext()) {
            System.out.println(count + " - " + curr.getItem());
            count++;
        }
    }

    public ListNode find(int index) throws ListIndexOutOfBoundsException {

        ListNode curr = head;
        if (index <= numItems && index >=1) {
            for (int i = 1; i < index; i++)
                curr = curr.getNext();
            return curr;
        }
        else {
            throw new ListIndexOutOfBoundsException("Index outOfBounds exception on find");
        }
    }

    public void add(int index, Object newDataItem) throws ListIndexOutOfBoundsException {
        if (index >= 1 && index <= numItems + 1) {
            if (index == 1) {
                ListNode newNode = new ListNode(newDataItem, head);
                head = newNode;
                if (tail == null)
                    tail = head;
            } else if (index == numItems + 1) {
                ListNode newNode = new ListNode(newDataItem);
                tail.setNext(newNode);
                tail = newNode;
            } else {
                ListNode prev = find(index - 1);
                ListNode newNode = new ListNode(newDataItem, prev.getNext());
                prev.setNext(newNode);
            }
            numItems++;
        } else {
            throw new ListIndexOutOfBoundsException("Index outOfBounds exception on add");
        }
    }


    public void addSortedAlphabetically(Object o) throws ListIndexOutOfBoundsException {
        Collator usCollator = Collator.getInstance(Locale.US);
        usCollator.setStrength(Collator.PRIMARY);
        if (head == null) {
            this.add(1, o);
        } else {
            int index = 1; // beginning
            for (ListNode curr = head; curr != null; curr = curr.getNext()) {
                if (usCollator.compare(((Product) o).getName(), ((Product) curr.getItem()).getName()) <= 0) {
                    this.add(index, o);
                    return;
                }
                index++;
            }
            this.add(numItems + 1, o);//case where object is the last (lexicographically) one in the list
        }
    }


    public void remove(int index) throws ListIndexOutOfBoundsException {
        if (index >= 1 && index <= numItems) {
            if (index == 1) {
                head = head.getNext();
                if (head == null)
                    tail = null;
            } else {
                ListNode prev = find(index - 1);
                ListNode curr = prev.getNext();
                prev.setNext(curr.getNext());
                if (index == numItems)
                    tail = prev;
            }
            numItems--;
        } else {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds exception on remove");
        }
    }

    public void removeByItem(Object o) throws Exception {
        ListNode curr = head;
        ListNode prev;
        for (int i = 1; i<=numItems;i++) {
            if ((curr.getItem()).equals(o)) {
                if (i == 1){
                    head = head.getNext();
                    if (head == null)
                        tail = null;
                }
                else if (i == numItems){
                    prev = find(i - 1);
                    tail = prev;
                }
                else {
                    prev = find(i - 1);
                    prev.setNext(curr.getNext());
                }
                break;
            }
        }
    }

    public void removeAll() {
        head = tail = null;
    }

    public Object get(int index) throws ListIndexOutOfBoundsException {
        if (index >= 1 && index <= numItems) {
            ListNode curr = find(index);
            return curr.getItem();
        } else {
            throw new ListIndexOutOfBoundsException("Index outOfBounds exception on get");
        }
    }

    public boolean exists(Object o) { //if an object exists in the list
        boolean exists = false;
        for (ListNode curr = head; curr != null; curr = curr.getNext()) {
            if ((curr.getItem()).equals(o)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public Object findItem(Object o) { //find specific item and return it
        for (ListNode curr = head; curr != null; curr = curr.getNext()) {
            if ((curr.getItem()).equals(o)) {
                return curr.getItem();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        int count = 1;
        String contents = "";
        for (ListNode curr = head; curr != null; curr = curr.getNext()) {
            contents += " " + count + ":" + curr.getItem();
            count++;
        }
        return contents;
    }
}

