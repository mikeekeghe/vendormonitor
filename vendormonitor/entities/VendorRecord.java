/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendormonitor.entities;

/**
 *
 * @author tn040764
 */
public class VendorRecord {

    private String id;
    private String vendor;
    private String vendorTMP;
    private String DateOfExpiry;
    private String ITSRSignedDate;
    private String Comments;
    private String description;

    public VendorRecord(String id, String vendor, String vendorTMP, String DateOfExpiry, String ITSRSignedDate, String Comments, String description) {
        this.id = id;
        this.vendor = vendor;
        this.vendorTMP = vendorTMP;
        this.DateOfExpiry = DateOfExpiry;
        this.ITSRSignedDate = ITSRSignedDate;
        this.Comments = Comments;
        this.description = description;
    }



    public VendorRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getVendorTMP() {
        return vendorTMP;
    }

    public void setVendorTMP(String vendorTMP) {
        this.vendorTMP = vendorTMP;
    }

    public String getDateOfExpiry() {
        return DateOfExpiry;
    }

    public void setDateOfExpiry(String DateOfExpiry) {
        this.DateOfExpiry = DateOfExpiry;
    }

    public String getITSRSignedDate() {
        return ITSRSignedDate;
    }

    public void setITSRSignedDate(String ITSRSignedDate) {
        this.ITSRSignedDate = ITSRSignedDate;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
