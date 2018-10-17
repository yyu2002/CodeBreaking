import java.lang.reflect.Array;
import java.util.ArrayList;

public class StatsObj {

    private ArrayList<String> dataStore;

    public StatsObj() {
        dataStore = new ArrayList<String>();
    }

    public void add(String item) {
        dataStore.add(item);
    }

    public String get(int index) {
        return dataStore.get(index);
    }

    public int getCountOf(String letter) {
        int count = 0;
        for (int i = 0; i < dataStore.size(); i++) {
            String l = dataStore.get(i);
            if (l.equals(letter))
                count++;
        }
        return count;
    }

    public int getTotalCount() {
        return dataStore.size();
    }

    public int getUniqueVals() {
        int count = 0;
        for (int i = 0; i < dataStore.size(); i++) {
            String letter = dataStore.get(i);
            boolean repeat = false;
            for (int j = 0; j < i; j++) {
                String letter2 = dataStore.get(j);
                if (letter2.equals(letter))
                    repeat = true;
            }
            if (!repeat)
                count++;
        }
        return count;
    }

    public String getMostFreq() {
        return getMostFreq(dataStore);
    }

    public String getMostFreq(ArrayList<String> data) {
        String string = "";
        int largestNumRepeats = 0;
        for (int i = 0; i < data.size(); i++) {
            int currentNumRepeats = getCountOf(data.get(i));
            if (currentNumRepeats > largestNumRepeats) {
                largestNumRepeats = currentNumRepeats;
                string = data.get(i);
            }
        }
        return string;
    }

    public ArrayList<String> top3() {
        return topN(3);
    }


    public ArrayList<String> topN(int num) {
        ArrayList<String> topN = new ArrayList<String>();
        ArrayList<String> copy = duplicateList(dataStore);

        while (num > 0) {
            String data = getMostFreq(copy);
            topN.add(data);
            int i = 0;
            while (i < copy.size()) {
                String data2 = copy.get(i);
                if (data.equals(data2)) {
                    copy.remove(i);
                } else
                    i++;
            }
            num--;
        }
        return topN;
    }

    public String[] topNArray(int num) {
        ArrayList<String> topN = topN(num);
        String[] topNArr = new String[num];
        for(int i = 0; i < num; i++){
            topNArr[i] = topN.get(i);
        }
        return topNArr;
    }

    public ArrayList<String> duplicateList(ArrayList<String> list) {
        ArrayList<String> copy = new ArrayList<String>();
        for (String string : list) {
            copy.add(string);
        }
        return copy;
    }

}
