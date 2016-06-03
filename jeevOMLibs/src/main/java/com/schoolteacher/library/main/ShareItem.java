/**
 * Project      : Awaaz
 * Filename     : ShareItem.java
 * Author       : nagaraj
 * Comments     :
 * Copyright    : © Copyright NDTV Convergence Limited 2011
 * Developed under contract by Robosoft Technologies
 * History      : NA
 */

package com.schoolteacher.library.main;

import android.net.Uri;

/**
 * @author Harisha B
 */
public class ShareItem {

    public String title;
    public String desc = "";

    /**
     * Content link
     */
    public String link;

    /**
     * Content type like text/plain, etc
     */
    public String contentType;

    // Deep Linking
    public String itemType;
    public String itemID;
    public String category;
    public Uri imageUri;
    public String imageUrl;

}
