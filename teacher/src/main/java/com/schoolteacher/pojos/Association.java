package com.schoolteacher.pojos;

public class Association {
    private int Id;
    private String AssociationName;
    private int AssociationIdKey;

    /**
     * @return the id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * @return the relation
     */
    public String getAssociationName() {
        return AssociationName;
    }

    /**
     * @param associationName the associationName to set
     */
    public void setAssociationName(String associationName) {
        AssociationName = associationName;
    }

    /**
     * @return the associationIdKey
     */
    public int getAssociationIdKey() {
        return AssociationIdKey;
    }

    /**
     * @param associationIdKey the associationIdKey to set
     */
    public void setAssociationIdKey(int associationIdKey) {
        AssociationIdKey = associationIdKey;
    }
}
