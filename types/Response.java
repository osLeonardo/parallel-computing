package types;

public class Response {
	@Override
	public String toString() {
		return "Response [fileName=" + fileName + ", lineNumber=" + lineNumber + "]";
	}
	private String fileName;
	private Integer lineNumber;
	
	public Response(String fileName, Integer lineNumber) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
}
