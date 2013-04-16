package com.squareup.picasso;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.squareup.picasso.Request.Builder;
import static com.squareup.picasso.Request.Type;
import static org.fest.assertions.api.Assertions.fail;
import static org.mockito.Mockito.mock;

@RunWith(PicassoTestRunner.class)
public class RequestTest {
  private static final String URL = "http://example.com/a.png";

  private Picasso picasso = mock(Picasso.class);
  private Builder builder = new Builder(picasso, URL, Type.STREAM);
  private Drawable drawable = new ColorDrawable(0);

  @Test public void invalidPlaceholderImage() {
    try {
      builder.placeholder(0);
      fail("Resource ID of zero should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.placeholder(null);
      fail("Null drawable should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.placeholder(1).placeholder(drawable);
      fail("Two placeholders should throw exception.");
    } catch (IllegalStateException expected) {
    }
    try {
      builder.placeholder(drawable).placeholder(1);
      fail("Two placeholders should throw exception.");
    } catch (IllegalStateException expected) {
    }
  }

  @Test public void invalidErrorImage() {
    try {
      builder.error(0);
      fail("Resource ID of zero should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.error(null);
      fail("Null drawable should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.error(1).error(drawable);
      fail("Two placeholders should throw exception.");
    } catch (IllegalStateException expected) {
    }
    try {
      builder.error(drawable).error(1);
      fail("Two placeholders should throw exception.");
    } catch (IllegalStateException expected) {
    }
  }

  @Test public void invalidResize() {
    try {
      builder.resize(-1, 10);
      fail("Negative width should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.resize(10, -1);
      fail("Negative height should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.resize(0, 10);
      fail("Zero width should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.resize(10, 0);
      fail("Zero height should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
  }

  @Test public void invalidScale() {
    try {
      builder.scale(-1);
      fail("Negative scale factor should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.scale(0);
      fail("Zero scale factor should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullTransformationsInvalid() {
    builder.transform(null);
  }

  @Test public void nullTargetsInvalid() {
    try {
      builder.into((ImageView) null);
      fail("Null ImageView should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
    try {
      builder.into((Target) null);
      fail("Null Target should throw exception.");
    } catch (IllegalArgumentException expected) {
    }
  }
}