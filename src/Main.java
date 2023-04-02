import Person.*;
import Library.*;

import java.security.Permission;
import java.util.*;
import Book.*;
public class Main {
    public static void main(String[] args) {
        MainService service = MainService.getInstance();
        service.main();
    }
}