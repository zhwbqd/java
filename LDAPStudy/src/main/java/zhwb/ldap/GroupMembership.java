package zhwb.ldap;
public class GroupMembership implements Comparable<GroupMembership>
{
	/** Identifier */
	private String id;
	
	/** Group Identifier */
	private String group;

	/**
	 * Parameterized Constructor
	 * @param id Identifier
	 * @param group Group Identifier
	 */
	public GroupMembership( final String id, final String group )
	{
		// Set the membership values
		this.setId( id );
		this.setGroup( group );
	}

	/**
	 * Get Identifier
	 * @return Id
	 */
	public final String getId()
	{
		return this.id;
	}

	/**
	 * Set Identifier
	 * @param id Id
	 */
	public final void setId( final String id )
	{
		if ( id == null ) {
			this.id = "";
		} else {
			this.id = id;
		}
	}

	/**
	 * Get Group Identifier
	 * @return Group
	 */
	public final String getGroup()
	{
		return this.group;
	}

	/**
	 * Set Group Identifier
	 * @param group Group
	 */
	public final void setGroup( final String group )
	{
		if ( group == null ) {
			this.group = "";
		} else {
			this.group = group;
		}
	}

	/**
	 * Compare Group Memberships
	 * @param other Object to compare to
	 * @return 1 ID Doesn't Match, -1  Group Doesn't Match, 0 Equivalent
	 */
	public final int compareTo( final GroupMembership other )
	{
		if ( other.getId().compareTo(this.getId()) != 0 ) {
			// ID doesn't match
			return 1;
		} else if ( other.getGroup().compareTo(this.getGroup()) != 0) {
			// Group doesn't match
			return -1;
		} else {
			// Equivalent
			return 0;
		}
	}
	
	/**
	 * Equals
	 * @param other Group Membership
	 * @return boolean
	 */
	public final boolean equals( final Object other ) {
		// Check if it is an instance of GroupMembership and use compareTo
		if ( other instanceof GroupMembership && this.compareTo((GroupMembership)other) == 0) {
			return true;
		}
		
		//Otherwise not equal
		return false;
	}
	
	/**
	 * Hash Code
	 * @return int
	 */
	public final int hashCode() {
		// Hash the two strings together
		String hash = this.getId() + this.getGroup();
		return hash.hashCode();
	}
}
