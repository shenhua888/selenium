package com.test.first_maven.test.jackson;

import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONObject2 extends JSONObject {
	private Logger log = LogManager.getFormatterLogger(this.getClass());
	@Override
	public JSONObject accumulate(String key, Object value) throws JSONException {
		// TODO Auto-generated method stub
		return super.accumulate(key, value);
	}

	@Override
	public JSONObject append(String key, Object value) throws JSONException {
		// TODO Auto-generated method stub
		return super.append(key, value);
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		try {
			return super.get(key);
		} catch(JSONException e) {
			log.info("JSONObject2.get error:"+e.getMessage());
			return null;
		}
	}

	@Override
	public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getEnum(clazz, key);
	}

	@Override
	public boolean getBoolean(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getBoolean(key);
	}

	@Override
	public BigInteger getBigInteger(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getBigInteger(key);
	}

	@Override
	public BigDecimal getBigDecimal(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getBigDecimal(key);
	}

	@Override
	public double getDouble(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getDouble(key);
	}

	@Override
	public int getInt(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getInt(key);
	}

	@Override
	public JSONArray getJSONArray(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getJSONArray(key);
	}

	@Override
	public JSONObject getJSONObject(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getJSONObject(key);
	}

	@Override
	public long getLong(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getLong(key);
	}

	@Override
	public String getString(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.getString(key);
	}

	@Override
	public boolean has(String key) {
		// TODO Auto-generated method stub
		return super.has(key);
	}

	@Override
	public JSONObject increment(String key) throws JSONException {
		// TODO Auto-generated method stub
		return super.increment(key);
	}

	@Override
	public boolean isNull(String key) {
		// TODO Auto-generated method stub
		return super.isNull(key);
	}

	@Override
	public Iterator<String> keys() {
		// TODO Auto-generated method stub
		return super.keys();
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return super.keySet();
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return super.length();
	}

	@Override
	public JSONArray names() {
		// TODO Auto-generated method stub
		return super.names();
	}

	@Override
	public Object opt(String key) {
		// TODO Auto-generated method stub
		return super.opt(key);
	}

	@Override
	public <E extends Enum<E>> E optEnum(Class<E> clazz, String key) {
		// TODO Auto-generated method stub
		return super.optEnum(clazz, key);
	}

	@Override
	public <E extends Enum<E>> E optEnum(Class<E> clazz, String key, E defaultValue) {
		// TODO Auto-generated method stub
		return super.optEnum(clazz, key, defaultValue);
	}

	@Override
	public boolean optBoolean(String key) {
		// TODO Auto-generated method stub
		return super.optBoolean(key);
	}

	@Override
	public boolean optBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return super.optBoolean(key, defaultValue);
	}

	@Override
	public double optDouble(String key) {
		// TODO Auto-generated method stub
		return super.optDouble(key);
	}

	@Override
	public BigInteger optBigInteger(String key, BigInteger defaultValue) {
		// TODO Auto-generated method stub
		return super.optBigInteger(key, defaultValue);
	}

	@Override
	public BigDecimal optBigDecimal(String key, BigDecimal defaultValue) {
		// TODO Auto-generated method stub
		return super.optBigDecimal(key, defaultValue);
	}

	@Override
	public double optDouble(String key, double defaultValue) {
		// TODO Auto-generated method stub
		return super.optDouble(key, defaultValue);
	}

	@Override
	public int optInt(String key) {
		// TODO Auto-generated method stub
		return super.optInt(key);
	}

	@Override
	public int optInt(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return super.optInt(key, defaultValue);
	}

	@Override
	public JSONArray optJSONArray(String key) {
		// TODO Auto-generated method stub
		return super.optJSONArray(key);
	}

	@Override
	public JSONObject optJSONObject(String key) {
		// TODO Auto-generated method stub
		return super.optJSONObject(key);
	}

	@Override
	public long optLong(String key) {
		// TODO Auto-generated method stub
		return super.optLong(key);
	}

	@Override
	public long optLong(String key, long defaultValue) {
		// TODO Auto-generated method stub
		return super.optLong(key, defaultValue);
	}

	@Override
	public String optString(String key) {
		// TODO Auto-generated method stub
		return super.optString(key);
	}

	@Override
	public String optString(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return super.optString(key, defaultValue);
	}

	@Override
	public JSONObject put(String key, boolean value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject put(String key, Collection<?> value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject put(String key, double value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject put(String key, int value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject put(String key, long value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject put(String key, Map<?, ?> value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject put(String key, Object value) throws JSONException {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public JSONObject putOnce(String key, Object value) throws JSONException {
		// TODO Auto-generated method stub
		return super.putOnce(key, value);
	}

	@Override
	public JSONObject putOpt(String key, Object value) throws JSONException {
		// TODO Auto-generated method stub
		return super.putOpt(key, value);
	}

	@Override
	public Object query(String jsonPointer) {
		// TODO Auto-generated method stub
		return super.query(jsonPointer);
	}

	@Override
	public Object optQuery(String jsonPointer) {
		// TODO Auto-generated method stub
		return super.optQuery(jsonPointer);
	}

	@Override
	public Object remove(String key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}

	@Override
	public boolean similar(Object other) {
		// TODO Auto-generated method stub
		return super.similar(other);
	}

	@Override
	public JSONArray toJSONArray(JSONArray names) throws JSONException {
		// TODO Auto-generated method stub
		return super.toJSONArray(names);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public String toString(int indentFactor) throws JSONException {
		// TODO Auto-generated method stub
		return super.toString(indentFactor);
	}

	@Override
	public Writer write(Writer writer) throws JSONException {
		// TODO Auto-generated method stub
		return super.write(writer);
	}

	@Override
	public Writer write(Writer writer, int indentFactor, int indent) throws JSONException {
		// TODO Auto-generated method stub
		return super.write(writer, indentFactor, indent);
	}

	@Override
	public Map<String, Object> toMap() {
		// TODO Auto-generated method stub
		return super.toMap();
	}

	public JSONObject2() {
		// TODO Auto-generated constructor stub
		super();
	}

	public JSONObject2(JSONTokener x) throws JSONException {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public JSONObject2(Map<?, ?> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	public JSONObject2(Object bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}

	public JSONObject2(String source) throws JSONException {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public JSONObject2(JSONObject jo, String[] names) {
		super(jo, names);
		// TODO Auto-generated constructor stub
	}

	public JSONObject2(Object object, String[] names) {
		super(object, names);
		// TODO Auto-generated constructor stub
	}

	public JSONObject2(String baseName, Locale locale) throws JSONException {
		super(baseName, locale);
		// TODO Auto-generated constructor stub
	}

}
