package javaLauncher;

class FileReadingException extends Exception {
    public FileReadingException(String errmessage, Throwable err) {
        super(errmessage, err);
    }
}