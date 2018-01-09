package com.example.omd.library.Login_Register.Registration;

import com.example.omd.library.Models.LibraryModel;
import com.example.omd.library.Models.NormalUserData;
import com.example.omd.library.Models.PublisherModel;

/**
 * Created by Delta on 24/12/2017.
 */

public interface ViewData {
    void setNormalUserFirstName_Error();
    void setNormalUserLastName_Error();
    void setNormalUserEmail_Error();
    void setNormalUser_invalidEmail_Error();
    void setNormalUserCountry_Error();
    void setNormalUserPassword_Error();

    void setPublisherFirstName_Error();
    void setPublisherLastName_Error();
    void setPublisherEmail_Error();
    void setPublisher_invalidEmail_Error();
    void setPublisherCountry_Error();
    void setPublisherPassword_Error();

    void setLibraryName_Error();
    void setLibraryEmail_Error();
    void setLibraryCommission_Error();
    void setLibraryOtherType_Error();
    void setLibraryCountry_Error();
    void setLibraryPassword_Error();
    void setLibraryLatitude_Error();
    void setLibraryLongitude_Error();

    void showProgressDialog();
    void hideProgressDialog();
    void navigateTO_HomeActivity();
    void setError(String error);
    void onNormalUserDataSuccess(NormalUserData normalUserData);
    void onPublisherDataSuccess(PublisherModel publisherModel);
    void onLibraryDataSuccess(LibraryModel libraryModel);
//
}