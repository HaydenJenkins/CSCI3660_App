package com.zybooks.csci3660app;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EventList {

    public static final String FILENAME = "eventslist.txt";

    private Context mContext;
    private ArrayList<String> mTaskList;

    public EventList(Context context) {
        mContext = context;
        mTaskList = new ArrayList<>();
    }

    public void addItem(String item) throws IllegalArgumentException {
        mTaskList.add(item);
    }


    public String[] getItems() {
        String[] items = new String[mTaskList.size()];
        return mTaskList.toArray(items);
    }

    public int size() {
        return mTaskList.size();
    }

    public boolean isEmpty() {
        return mTaskList.isEmpty();
    }
    public String get(int index) {
        return mTaskList.get(index);
    }

    public void clear() {
        mTaskList.clear();
    }

    public void saveToFile() throws IOException {

        // Write list to file in internal storage
        FileOutputStream outputStream = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        writeListToStream(outputStream);
    }

    public void readFromFile() throws IOException {

        // Read in list from file in internal storage
        FileInputStream inputStream = mContext.openFileInput(FILENAME);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            mTaskList.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                mTaskList.add(line);
            }
        }
        catch (FileNotFoundException ex) {
            // Ignore
        }
    }

    private void writeListToStream(FileOutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        for (String item : mTaskList) {
            writer.println(item);
        }
        writer.close();
    }

}
