/*
 * AndroidMaterialPreferences Copyright 2014 - 2015 Michael Rapp
 *
 * This program is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published 
 * by the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>. 
 */
package de.mrapp.android.preference;

import static de.mrapp.android.preference.util.Condition.ensureNotNull;

import java.util.LinkedHashSet;
import java.util.Set;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import de.mrapp.android.dialog.MaterialDialogBuilder;

/**
 * A preference, which allows to enter a text via an {@link EditText} widget.
 * The entered text will only be persisted, if confirmed by the user.
 * 
 * @author Michael Rapp
 *
 * @since 1.0.0
 */
public class EditTextPreference extends AbstractDialogPreference {

	/**
	 * Defines the interface, a class, which should be able to validate the text
	 * of an {@link EditTextPreference}, must implement.
	 */
	public interface Validator {

		/**
		 * Validates the text of an {@link EditTextPreference}.
		 * 
		 * @param text
		 *            The text, which should be validated
		 * 
		 * @return A string, which indicates a validation error or null, if the
		 *         given text is valid
		 */
		String validate(String text);

	};

	/**
	 * A data structure, which allows to save the internal state of an
	 * {@link EditTextPreference}.
	 */
	public static class SavedState extends BaseSavedState {

		/**
		 * A creator, which allows to create instances of the class
		 * {@link EditTextPreference} from parcels.
		 */
		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

			@Override
			public SavedState createFromParcel(final Parcel in) {
				return new SavedState(in);
			}

			@Override
			public SavedState[] newArray(final int size) {
				return new SavedState[size];
			}

		};

		/**
		 * The saved value of the attribute "text".
		 */
		public String text;

		/**
		 * Creates a new data structure, which allows to store the internal
		 * state of an {@link EditTextPreference}. This constructor is called by
		 * derived classes when saving their states.
		 * 
		 * @param superState
		 *            The state of the superclass of this view, as an instance
		 *            of the type {@link Parcelable}
		 */
		public SavedState(final Parcelable superState) {
			super(superState);
		}

		/**
		 * Creates a new data structure, which allows to store the internal
		 * state of an {@link EditTextPreference}. This constructor is used when
		 * reading from a parcel. It reads the state of the superclass.
		 * 
		 * @param source
		 *            The parcel to read read from as a instance of the class
		 *            {@link Parcel}
		 */
		public SavedState(final Parcel source) {
			super(source);
			text = source.readString();
		}

		@Override
		public final void writeToParcel(final Parcel destination,
				final int flags) {
			super.writeToParcel(destination, flags);
			destination.writeString(text);
		}

	};

	/**
	 * The {@link EditText} widget, which allows the user to enter a text.
	 */
	private EditText editText;

	/**
	 * The currently persisted text.
	 */
	private String text;

	/**
	 * The validators, which have been added to the preference.
	 */
	private transient Set<Validator> validators;

	/**
	 * Initializes the preference.
	 */
	private void initialize() {
		this.validators = new LinkedHashSet<EditTextPreference.Validator>();
		setPositiveButtonText(android.R.string.ok);
		setNegativeButtonText(android.R.string.cancel);
	}

	/**
	 * Creates a new preference, which allows to enter a text via an
	 * {@link EditText} widget.
	 * 
	 * @param context
	 *            The context, which should be used by the preference, as an
	 *            instance of the class {@link Context}
	 */
	public EditTextPreference(final Context context) {
		super(context);
		initialize();
	}

	/**
	 * Creates a new preference, which allows to enter a text via an
	 * {@link EditText} widget.
	 * 
	 * @param context
	 *            The context, which should be used by the preference, as an
	 *            instance of the class {@link Context}
	 * @param attributeSet
	 *            The attributes of the XML tag that is inflating the
	 *            preference, as an instance of the type {@link AttributeSet}
	 */
	public EditTextPreference(final Context context,
			final AttributeSet attributeSet) {
		super(context, attributeSet);
		initialize();
	}

	/**
	 * Creates a new preference, which allows to enter a text via an
	 * {@link EditText} widget.
	 * 
	 * @param context
	 *            The context, which should be used by the preference, as an
	 *            instance of the class {@link Context}
	 * @param attributeSet
	 *            The attributes of the XML tag that is inflating the
	 *            preference, as an instance of the type {@link AttributeSet}
	 * @param defaultStyle
	 *            The default style to apply to this preference. If 0, no style
	 *            will be applied (beyond what is included in the theme). This
	 *            may either be an attribute resource, whose value will be
	 *            retrieved from the current theme, or an explicit style
	 *            resource
	 */
	public EditTextPreference(final Context context,
			final AttributeSet attributeSet, final int defaultStyle) {
		super(context, attributeSet, defaultStyle);
		initialize();
	}

	/**
	 * Creates a new preference, which allows to enter a text via an
	 * {@link EditText} widget.
	 * 
	 * @param context
	 *            The context, which should be used by the preference, as an
	 *            instance of the class {@link Context}
	 * @param attributeSet
	 *            The attributes of the XML tag that is inflating the
	 *            preference, as an instance of the type {@link AttributeSet}
	 * @param defaultStyle
	 *            The default style to apply to this preference. If 0, no style
	 *            will be applied (beyond what is included in the theme). This
	 *            may either be an attribute resource, whose value will be
	 *            retrieved from the current theme, or an explicit style
	 *            resource
	 * @param defaultStyleResource
	 *            A resource identifier of a style resource that supplies
	 *            default values for the preference, used only if the default
	 *            style is 0 or can not be found in the theme. Can be 0 to not
	 *            look for defaults
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public EditTextPreference(final Context context,
			final AttributeSet attributeSet, final int defaultStyle,
			final int defaultStyleResource) {
		super(context, attributeSet, defaultStyle, defaultStyleResource);
		initialize();
	}

	/**
	 * Returns the currently persisted text of the preference.
	 * 
	 * @return The currently persisted text as a {@link String}
	 */
	public final String getText() {
		return text;
	}

	/**
	 * Sets the current text of the preference. By setting a value, it will be
	 * persisted.
	 * 
	 * @param text
	 *            The text, which should be set, as a {@link String}
	 */
	public final void setText(final String text) {
		boolean hasDisabledDependents = shouldDisableDependents();
		this.text = text;
		persistString(text);
		boolean isDisabelingDependents = shouldDisableDependents();

		if (isDisabelingDependents != hasDisabledDependents) {
			notifyDependencyChange(isDisabelingDependents);
		}
	}

	/**
	 * Adds a new validator to the preference.
	 * 
	 * @param validator
	 *            The validator, which should be added, as an instance of the
	 *            type {@link Validator}
	 */
	public final void addValidator(final Validator validator) {
		ensureNotNull(validator, "The validator may not be null");
		this.validators.add(validator);
	}

	/**
	 * Removes a specific validator from the preference.
	 * 
	 * @param validator
	 *            The validator, which should be removed, as an instance of the
	 *            type {@link Validator}
	 */
	public final void removeValidator(final Validator validator) {
		ensureNotNull(validator, "The validator may not be null");
		this.validators.remove(validator);
	}

	@Override
	public final CharSequence getSummary() {
		if (isValueShownAsSummary()) {
			return getText();
		} else {
			return super.getSummary();
		}
	}

	@Override
	public final boolean shouldDisableDependents() {
		return TextUtils.isEmpty(getText()) || super.shouldDisableDependents();
	}

	@Override
	protected final void onPrepareDialog(
			final MaterialDialogBuilder dialogBuilder) {
		editText = (EditText) View.inflate(getContext(), R.layout.edit_text,
				null);
		editText.setText(getText());
		dialogBuilder.setView(editText);
	}

	@Override
	protected final boolean onValidate() {
		for (Validator validator : validators) {
			String result = validator.validate(editText.getText().toString());

			if (result != null) {
				editText.setError(result);
				editText.requestFocus();
				return false;
			}
		}

		return true;
	}

	@Override
	protected final void onDialogClosed(final boolean positiveResult) {
		if (positiveResult) {
			String newValue = editText.getText().toString();

			if (callChangeListener(newValue)) {
				setText(newValue);
			}
		}

		editText = null;
	}

	@Override
	protected final boolean needInputMethod() {
		return true;
	}

	@Override
	protected final Object onGetDefaultValue(final TypedArray typedArray,
			final int index) {
		return typedArray.getString(index);
	}

	@Override
	protected final void onSetInitialValue(final boolean restoreValue,
			final Object defaultValue) {
		setText(restoreValue ? getPersistedString(getText())
				: (String) defaultValue);
	}

	@Override
	protected final Parcelable onSaveInstanceState() {
		Parcelable parcelable = super.onSaveInstanceState();

		if (!isPersistent()) {
			SavedState savedState = new SavedState(parcelable);
			savedState.text = getText();
			return savedState;
		}

		return parcelable;
	}

	@Override
	protected final void onRestoreInstanceState(final Parcelable state) {
		if (state != null && state instanceof SavedState) {
			SavedState savedState = (SavedState) state;
			setText(savedState.text);
			super.onRestoreInstanceState(savedState.getSuperState());
		} else {
			super.onRestoreInstanceState(state);
		}
	}

}