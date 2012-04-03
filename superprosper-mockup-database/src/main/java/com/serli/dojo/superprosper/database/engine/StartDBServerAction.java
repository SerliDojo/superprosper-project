package com.serli.dojo.superprosper.database.engine;

import java.io.OutputStream;

import org.jdesktop.application.Task;

import com.serli.dojo.superprosper.database.DBMockupApplication;

public class StartDBServerAction extends Task<DerbyEngine, Void> {

	private OutputStream out = null;

	public StartDBServerAction(DBMockupApplication app, OutputStream out) {
		super(app);
		this.out = out;
	}

	@Override
	protected DerbyEngine doInBackground() throws Exception {
		DerbyEngine engine = new DerbyEngine();
		engine.start(out);
		return engine;
	}

	@Override
	protected void succeeded(DerbyEngine engine) {
		super.succeeded(engine);
		((DBMockupApplication) getApplication()).setDerbyUtil(engine);
	}
}
