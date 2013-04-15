/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author DragønEye
 */
public class Browser {

    private File file;
    private String fileName;
    private boolean bool;

    public void browser() {
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new Browser.ExtensionFileFilter(".pdf", new String[]{"PDF"});
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                file = chooser.getSelectedFile();
                fileName = file.getAbsolutePath();
                if ("person.txt".equals(file.getName())) {
                    bool = true;
                } else {
                    bool = false;
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                bool = false;
                break;
            case JFileChooser.ERROR_OPTION:
                bool = false;
                break;
        }
    }

    class ExtensionFileFilter extends FileFilter {

        String description;
        String extensions[];

        public ExtensionFileFilter(String description, String extension) {
            this(description, new String[]{extension});
        }

        public ExtensionFileFilter(String description, String extensions[]) {
            if (description == null) {
                this.description = extensions[0];
            } else {
                this.description = description;
            }
            this.extensions = (String[]) extensions.clone();
            toLower(this.extensions);
        }

        private void toLower(String array[]) {
            for (int i = 0, n = array.length; i < n; i++) {
                array[i] = array[i].toLowerCase();
            }
        }

        public String getDescription() {
            return description;
        }

        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            } else {
                String path = file.getAbsolutePath().toLowerCase();
                for (int i = 0, n = extensions.length; i < n; i++) {
                    String extension = extensions[i];
                    if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isBool() {
        return bool;
    }
}
