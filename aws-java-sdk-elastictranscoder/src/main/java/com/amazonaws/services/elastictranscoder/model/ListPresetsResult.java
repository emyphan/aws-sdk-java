/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.elastictranscoder.model;

import java.io.Serializable;

/**
 * <p>
 * The <code>ListPresetsResponse</code> structure.
 * </p>
 */
public class ListPresetsResult implements Serializable {

    /**
     * An array of <code>Preset</code> objects.
     */
    private com.amazonaws.internal.ListWithAutoConstructFlag<Preset> presets;

    /**
     * A value that you use to access the second and subsequent pages of
     * results, if any. When the presets fit on one page or when you've
     * reached the last page of results, the value of
     * <code>NextPageToken</code> is <code>null</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>^\d{13}-\w{6}$<br/>
     */
    private String nextPageToken;

    /**
     * An array of <code>Preset</code> objects.
     *
     * @return An array of <code>Preset</code> objects.
     */
    public java.util.List<Preset> getPresets() {
        if (presets == null) {
              presets = new com.amazonaws.internal.ListWithAutoConstructFlag<Preset>();
              presets.setAutoConstruct(true);
        }
        return presets;
    }
    
    /**
     * An array of <code>Preset</code> objects.
     *
     * @param presets An array of <code>Preset</code> objects.
     */
    public void setPresets(java.util.Collection<Preset> presets) {
        if (presets == null) {
            this.presets = null;
            return;
        }
        com.amazonaws.internal.ListWithAutoConstructFlag<Preset> presetsCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<Preset>(presets.size());
        presetsCopy.addAll(presets);
        this.presets = presetsCopy;
    }
    
    /**
     * An array of <code>Preset</code> objects.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param presets An array of <code>Preset</code> objects.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public ListPresetsResult withPresets(Preset... presets) {
        if (getPresets() == null) setPresets(new java.util.ArrayList<Preset>(presets.length));
        for (Preset value : presets) {
            getPresets().add(value);
        }
        return this;
    }
    
    /**
     * An array of <code>Preset</code> objects.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param presets An array of <code>Preset</code> objects.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public ListPresetsResult withPresets(java.util.Collection<Preset> presets) {
        if (presets == null) {
            this.presets = null;
        } else {
            com.amazonaws.internal.ListWithAutoConstructFlag<Preset> presetsCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<Preset>(presets.size());
            presetsCopy.addAll(presets);
            this.presets = presetsCopy;
        }

        return this;
    }

    /**
     * A value that you use to access the second and subsequent pages of
     * results, if any. When the presets fit on one page or when you've
     * reached the last page of results, the value of
     * <code>NextPageToken</code> is <code>null</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>^\d{13}-\w{6}$<br/>
     *
     * @return A value that you use to access the second and subsequent pages of
     *         results, if any. When the presets fit on one page or when you've
     *         reached the last page of results, the value of
     *         <code>NextPageToken</code> is <code>null</code>.
     */
    public String getNextPageToken() {
        return nextPageToken;
    }
    
    /**
     * A value that you use to access the second and subsequent pages of
     * results, if any. When the presets fit on one page or when you've
     * reached the last page of results, the value of
     * <code>NextPageToken</code> is <code>null</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>^\d{13}-\w{6}$<br/>
     *
     * @param nextPageToken A value that you use to access the second and subsequent pages of
     *         results, if any. When the presets fit on one page or when you've
     *         reached the last page of results, the value of
     *         <code>NextPageToken</code> is <code>null</code>.
     */
    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
    
    /**
     * A value that you use to access the second and subsequent pages of
     * results, if any. When the presets fit on one page or when you've
     * reached the last page of results, the value of
     * <code>NextPageToken</code> is <code>null</code>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>^\d{13}-\w{6}$<br/>
     *
     * @param nextPageToken A value that you use to access the second and subsequent pages of
     *         results, if any. When the presets fit on one page or when you've
     *         reached the last page of results, the value of
     *         <code>NextPageToken</code> is <code>null</code>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public ListPresetsResult withNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPresets() != null) sb.append("Presets: " + getPresets() + ",");
        if (getNextPageToken() != null) sb.append("NextPageToken: " + getNextPageToken() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getPresets() == null) ? 0 : getPresets().hashCode()); 
        hashCode = prime * hashCode + ((getNextPageToken() == null) ? 0 : getNextPageToken().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof ListPresetsResult == false) return false;
        ListPresetsResult other = (ListPresetsResult)obj;
        
        if (other.getPresets() == null ^ this.getPresets() == null) return false;
        if (other.getPresets() != null && other.getPresets().equals(this.getPresets()) == false) return false; 
        if (other.getNextPageToken() == null ^ this.getNextPageToken() == null) return false;
        if (other.getNextPageToken() != null && other.getNextPageToken().equals(this.getNextPageToken()) == false) return false; 
        return true;
    }
    
}
    