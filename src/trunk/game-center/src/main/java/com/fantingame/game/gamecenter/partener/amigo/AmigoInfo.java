package com.fantingame.game.gamecenter.partener.amigo;

/**
 * 
 * 
 * @author yezp
 */
public class AmigoInfo {

	/**
	 * status
	 */
	public String r;
	public String wid;
	/**
	 * userId
	 */
	public String u;
	public String tn;
	public String na;
	public String ptr;
	public String ul;
	public String sty;
	public Ply ply;
	public String e;
	public String err;

	public class Ply {
		public String a;
		public String pid;
		public String na;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getNa() {
			return na;
		}

		public void setNa(String na) {
			this.na = na;
		}

	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getNa() {
		return na;
	}

	public void setNa(String na) {
		this.na = na;
	}

	public String getPtr() {
		return ptr;
	}

	public void setPtr(String ptr) {
		this.ptr = ptr;
	}

	public String getUl() {
		return ul;
	}

	public void setUl(String ul) {
		this.ul = ul;
	}

	public String getSty() {
		return sty;
	}

	public void setSty(String sty) {
		this.sty = sty;
	}

	public Ply getPly() {
		return ply;
	}

	public void setPly(Ply ply) {
		this.ply = ply;
	}

}
