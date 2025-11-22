package com.example.eeza.backendbuhoseat.utils;

public class Constants {
    //General endpoints
    public static final String API = "/api";
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String GET_ONE = "/get";
    public static final String GET_ALL = "/getAll";
    //Endpoints for user
    public static final String USER = "/user";
    public static final String UPDATE_PASSWORD =  "/updatePass";
    public static final String BY_ID_USER = "/{idUser}";
    public static final String BY_EMAIL_USER = "/{email}";
    //Endpoint for Local
    public static final String LOCAL = "/local";
    public static final String BY_ID_LOCAL = "/{idLocal}";
    //Endpoint for plate
    public static final String PLATE = "/plate";
    public static final String BY_ID_PLATE = "/{idPlate}";
    //Endpoints for favorite
    public static final String FAVORITE = "/favorite";
    //Endpoint for Review
    public static final String REVIEW = "/review";
    public static final String BY_ID_REVIEW = "/{idReview}";
    //Endpoint for Review
    public static final String SUBREVIEW = "/subReview";
    public static final String BY_ID_SUBREVIEW = "/{idSubReview}";
    //Auth
    public static final String AUTH = "/auth";
    public static final String LOGIN = "/login";

    //Entities
    public static final String ENTITY_USER =  "User";
    public static final String ENTITY_LOCAL =  "Local";
    public static final String ENTITY_PLATE =  "Plate";
    public static final String ENTITY_FAVORITE =  "Favorite";
    public static final String ENTITY_REVIEW =  "Review";
    public static final String ENTITY_SUBREVIEW =  "Subreview";

    //Exceptions Message
    public static final String NOT_FOUND =  " Not Found";
    public static final String JSON_MAPPER = "Cannot map request";
    public static final String NOT_PASSWORD_EQUALS = "Passwords don't match";
    public static final String HAVE_REVIEW = " already has a review";
    public static final String DIRECTION_EXIST ="Direction already exists";

    //Response Message
    public static final String CREATED =  " Created Successfully";
    public static final String UPDATED_PASS =  "Password Updated Successfully";
    public static final String UPDATED =  " Updated Successfully";
    public static final String DELETED =  " Deleted Successfully";
    public static final String FOUND  =  " Found";
    public static final String EXISTS = " Already Exists";

    //Validations message
    public static final String EMPTY_USER_NAME = "Username cannot be empty";
    public static final String EMPTY_EMAIL = "Email address cannot be empty";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String EMPTY_PASSWORD = "Password cannot be empty";
    public static final String NOT_LONG_PASSWORD = "Password must have 6 characters";
    public static final String NULL_USER_ROL = "Role cannot be null";
    public static final String NOT_ALPHANUMERIC_USER_PASSWORD = "Password must be alphanumeric";
    public static final String INVALID_FORMAT_IMAGE = "Invalid format image";
    public static final String EMPTY_LOCAL_NAME = "Local name cannot be empty";
    public static final String EMPTY_LOCAL_DESCRIPTION = "Local description cannot be empty";
    public static final String EMPTY_LOCAL_PHONE = "Local phone cannot be empty";
    public static final String EMPTY_LOCAL_DIRECTION = "Local direction cannot be empty";
    public static final String EMPTY_LOCAL_SCHEDULE = "Local schedule cannot be empty";
    public static final String INVALID_PHONE_FORMAT = "Invalid format phone";
    public static final String ID_EMPTY = "Id cannot be empty";
    public static final String EMPTY_IMAGE = "Image cannot be empty";
    public static final String EMPTY_PLATE_NAME = "Plate name cannot be empty";
    public static final String PRICE_INVALID = "Price cannot be zero or less";
    public static final String LOCAL_EMPTY = "Local cannot be empty";
    public static final String PRICE_EMPTY ="Price cannot be null";
    public static final String EMPTY_CONTENT = "Content cannot be empty";
    public static final String LESS_ZERO_STARS = "Stars cannot be negative";
    public static final String MORE_FIVE_STARS = "Stars cannot be more than 5";

    //Path file
    public static final String PATH_FILE = "uploads";
    public static final String CONTENT_TYPE_DEFAULT = "application/octet-stream";
}
