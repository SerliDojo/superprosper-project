package com.serli.dojo.superprosper.database.task;

import org.jdesktop.application.Task;

import com.serli.dojo.superprosper.database.DBMockupApplication;
import com.serli.dojo.superprosper.database.engine.DerbyEngine;

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
