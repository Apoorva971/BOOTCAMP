package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CategoryMetaDataField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    @Column(unique = true)
    private  String Name;
    @OneToMany(mappedBy = "categoryMetaDataField")
    private Set<CategoryMetadataFieldValues> metaDataFieldvalues;

    public CategoryMetaDataField(@NotNull String name, Set<CategoryMetadataFieldValues> metaDataFieldvalues) {
        Name = name;
        this.metaDataFieldvalues = metaDataFieldvalues;
    }

    public CategoryMetaDataField(@NotNull String name) {
        Name = name;
    }

    public CategoryMetaDataField(){

    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<CategoryMetadataFieldValues> getMetaDataFieldvalues() {
        return metaDataFieldvalues;
    }

    public void setMetaDataFieldvalues(Set<CategoryMetadataFieldValues> metaDataFieldvalues) {
        this.metaDataFieldvalues = metaDataFieldvalues;
    }
    public void addfieldvalues(CategoryMetadataFieldValues metaDataFieldvalue){
        if(metaDataFieldvalue !=null){
            if(metaDataFieldvalues==null)
                metaDataFieldvalues = new HashSet<>();
            metaDataFieldvalues.add(metaDataFieldvalue);
            metaDataFieldvalue.setCategoryMetadataField(this);

        }
    }
}
