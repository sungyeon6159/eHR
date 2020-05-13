/**
 * 
 */
package com.sist.ehr.board.service;

import com.sist.ehr.cmn.DTO;

/**
 * @author sist
 *
 */
public class BoardVO extends DTO {
	/**게시글_순번 */
	private int boardId   ; 
	/**제목 */
	private String title  ; 
	/**조회수 */
	private int readCnt   ; 
	/**내용 */
	private String contents  ; 
	/**등록자ID */
	private String regId     ; 
	/**등록일 */
	private String regDt     ; 
	
	public BoardVO() {}

	public BoardVO(int boardId, String title, int readCnt, String contents, String regId, String regDt) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.readCnt = readCnt;
		this.contents = contents;
		this.regId = regId;
		this.regDt = regDt;
	}

	/**
	 * @return the boardId
	 */
	public int getBoardId() {
		return boardId;
	}

	/**
	 * @param boardId the boardId to set
	 */
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the readCnt
	 */
	public int getReadCnt() {
		return readCnt;
	}

	/**
	 * @param readCnt the readCnt to set
	 */
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "BoardVO [boardId=" + boardId + ", title=" + title + ", readCnt=" + readCnt + ", contents=" + contents
				+ ", regId=" + regId + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardId;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + readCnt;
		result = prime * result + ((regId == null) ? 0 : regId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		if (boardId != other.boardId)
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (readCnt != other.readCnt)
			return false;
		if (regId == null) {
			if (other.regId != null)
				return false;
		} else if (!regId.equals(other.regId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
