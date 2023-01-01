package ru.itis.prytkovd.webapp.util;

public class WebPaths {
    private static final String PAGE_PREFIX = "/WEB-INF/views";
    private static final String PAGE_POSTFIX = ".jsp";

    public static final String SIGN_UP = "/sign_up";
    public static final String SIGN_IN = "/sign_in";
    public static final String SIGN_OUT = "/sign_out";
    public static final String DASHBOARD = "/dashboard";
    public static final String ROOMBOARD = "/dashboard/room";
    public static final String CREATE_ROOM = "/rooms/create";
    public static final String DELETE_ROOM = "/rooms/delete";
    public static final String JOIN_ROOM = "/rooms/join";
    public static final String ROOM_NOT_FOUND = "/rooms/not_found";

    public static final String SIGN_UP_PAGE = page(SIGN_UP);
    public static final String SIGN_IN_PAGE = page(SIGN_IN);
    public static final String DASHBOARD_PAGE = page(DASHBOARD);
    public static final String ROOMBOARD_PAGE = page("/roomboard");
    public static final String CREATE_ROOM_PAGE = page("/create_room");

    private static String page(String path) {
        return PAGE_PREFIX + path + PAGE_POSTFIX;
    }
}
