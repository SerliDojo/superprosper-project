package com.serli.dojo.superprosper.database.engine;

import org.jdesktop.application.Task;

import com.serli.dojo.superprosper.database.DBMockupApplication;

public class StopDBServerAction extends Task<Void, Void> {

	private DerbyEngine engine = null;

	public StopDBServerAction(DBMockupApplication app, DerbyEngine engine) {
		super(app);
		this.engine = engine;
	}

	@Override
	protected Void doInBackground() throws Exception {
		engine.stop();
		return null;
	}

	@Override
	protected void succeeded(Void nothing) {
		super.succeeded(nothing);
		((DBMockupApplication) getApplication()).setDerbyUtil(null);
	}
}
