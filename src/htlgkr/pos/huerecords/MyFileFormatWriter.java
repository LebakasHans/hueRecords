package htlgkr.pos.huerecords;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class MyFileFormatWriter {
    private List<Person> personList;
    private FileOutputStream fos;

    public MyFileFormatWriter(List<Person> personList, FileOutputStream fos) {
        this.personList = personList;
        this.fos = fos;
    }

    public void save(){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))){
            for (Person p : personList) {
                for (Field field : p.getClass().getDeclaredFields()){
                    field.setAccessible(true);
                    if(!field.getName().equals("nextId")) {

                        writer.write(field.getName() + "=" + field.get(p));
                        writer.newLine();
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

