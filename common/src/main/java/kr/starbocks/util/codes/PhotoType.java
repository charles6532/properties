/**
 * 
 */
package kr.starbocks.util.codes;

/**
 * This defines codes for payment status 
 * 
 * @author Playdata
 * @since 1.8
 */
public enum PhotoType {
   INTERNAL("PT01", "내부 사진", "Photos of Internal house",""),
   OUTSIDE("PT02", "외부 사진", "Photos of outside of house",""),
   GROUND("PT03", "평면도", "Photos of a ground plan",""),
   COMPLEX("PT04", "단지사진", "Photos of a complex",""),
   RIGHT("PT05", "등기부등본", "Photos of a right","")
   ;
   
   private String code;
   private String name;
   private String alias;
   private String parent;
   private PhotoType(final String code, final String name, final String alias, final String parent) {
      this.code = code;
      this.name = name;
      this.alias = alias;
      this.parent = parent;      
   }
   /**
    * @return the code for payment status
    */
   public String getCode() {
      return code;
   }
   /**
    * @param code the code to set
    */
   public void setCode(String code) {
      this.code = code;
   }
   /**
    * @return the name for payment status
    */
   public String getName() {
      return name;
   }
   /**
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }
   /**
    * @return the alias for payment status
    */
   public String getAlias() {
      return alias;
   }
   /**
    * @param alias the alias to set
    */
   public void setAlias(String alias) {
      this.alias = alias;
   }
   /**
    * @return the parent code for this payment status
    */
   public String getParent() {
      return parent;
   }
   /**
    * @param parent the parent to set
    */
   public void setParent(String parent) {
      this.parent = parent;
   }
}