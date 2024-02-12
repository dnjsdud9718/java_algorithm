package SSAFY.B형.week1.병사관리;

/*
  이 문제에서 LinkedList<> 라이브러리 사용하면 안된다. addAll이 오래 걸린다.
  addAll(Collection<? extends E> c) => c의 각 엘레먼트를 하나씩 add한다.-> 오래 걸린다.
 */


import java.util.LinkedList;

class UserSolution
{
    public class Node{
        int id;
        int v;
        Node prev;
        Node next;

        public Node(int id, int v, Node prev, Node next) {
            this.id = id;
            this.v = v;
            this.prev = prev;
            this.next = next;
        }
    }
    public class DoubleLinkedList {
        Node first;
        Node last;
        int size;

        public DoubleLinkedList() {
            this.size = 0;
            this.first = null;
            this.last = null;
        }

        public void linkFirst(int id, int v) {
            Node f = first;
            Node newNode = new Node(id, v, null, f);
            first = newNode;
            if (f == null) {
                last = null;
            } else {
                f.prev = newNode;
            }
            size++;
        }

        public void linkLast(int id, int v) {
            Node l = last;
            Node newNode = new Node(id, v, l, null);
            last = newNode;
            if (l == null) {
                first = newNode;
            } else {
                l.next = newNode;
            }
            size++;
        }
        public void concat(Node first2, Node last2, int size2){
            if (last == null) {
                first = first2;
            } else {
                last.next = first2;
                first2.prev = last;
            }
            last = last2;
            size += size2;
        }
        public int getSize(){
            return size;
        }
    }
    DoubleLinkedList[][] list;
    int[] myTeams;
    int[] myVers;
    public void init()
    {
        list = new DoubleLinkedList[6][6];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                list[i][j] = new DoubleLinkedList();
            }
        }
        myTeams = new int[100001];
        myVers = new int[100001];
    }

    public void hire(int mID, int mTeam, int mScore)
    {
        myTeams[mID] = mTeam;
        if(myVers[mID] < 0) myVers[mID] *= -1;
        myVers[mID]++;
        list[mTeam][mScore].linkLast(mID, myVers[mID]);
    }

    public void fire(int mID)
    {
        myVers[mID] *= -1;
    }

    public void updateSoldier(int mID, int mScore)
    {
        hire(mID, myTeams[mID], mScore);
    }

    public void updateTeam(int mTeam, int mChangeScore)
    {
        if (mChangeScore > 0) {
            for (int i = 4; i >= 1; i--) {
                int j = Math.min(i + mChangeScore, 5);
                if(i == j || list[mTeam][i].getSize() == 0) continue;
                list[mTeam][j].concat(list[mTeam][i].first, list[mTeam][i].last, list[mTeam][i].getSize());
                list[mTeam][i] = new DoubleLinkedList();
            }
        } else if (mChangeScore < 0) {
            for (int i = 2; i <= 5; i++) {
                int j = Math.max(i + mChangeScore, 1);
                if(i == j || list[mTeam][i].getSize() == 0) continue;
                list[mTeam][j].concat(list[mTeam][i].first, list[mTeam][i].last, list[mTeam][i].getSize());
                list[mTeam][i] = new DoubleLinkedList();
            }
        }
    }

    public int bestSoldier(int mTeam)
    {
        for (int i = 5; i >= 1; i--) {
            if(list[mTeam][i].getSize() == 0) continue;
            int id = 0;
            Node x = list[mTeam][i].first;
            while(x != null){
                if(x.v != myVers[x.id]) {
                    x = x.next;
                    continue;
                }
                id = Math.max(id, x.id);
                x = x.next;
            }
            if(id == 0) {
                continue;
            }
            return id;
        }
        return 0;
    }
}