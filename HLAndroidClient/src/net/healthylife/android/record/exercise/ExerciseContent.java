package net.healthylife.android.record.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ExerciseContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<ExerciseItem> ITEMS = new ArrayList<ExerciseItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, ExerciseItem> ITEM_MAP = new HashMap<String, ExerciseItem>();

	static {
		// Add 3 sample items.
		addItem(new ExerciseItem("2", "Item 2"));
		addItem(new ExerciseItem("3", "Item 3"));
	}

	public static void addItem(ExerciseItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
	
	public static void addItem(String id, String content) {
		if (ITEM_MAP.containsKey(id))
			return;
		ExerciseItem item = new ExerciseItem(id, content);
		ITEMS.add(item);
		ITEM_MAP.put(id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class ExerciseItem {
		public String id;
		public String content;

		public ExerciseItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
