package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

public abstract class ByteArrayValGenerationServiceFactory {

  private ByteArrayValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link ByteArrayValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodByteArrayValGenerator}</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>None</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static ByteArrayValGenerationService byteArray() {
    ByteArrayValGenerationService service = new ByteArrayValGenerationService();
    service.addGoodGenerator(new GoodByteArrayValGenerator());
    return service;
  }

  /**
   * Create a {@link ByteArrayValGenerationService} with good {@link FixedByteArrayValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static ByteArrayValGenerationService byteArrayWithGood(Byte[] value, Byte[]... values) {
    ByteArrayValGenerationService service = new ByteArrayValGenerationService();
    service.addGoodGenerator(new FixedByteArrayValGenerator(value));
    for (Byte[] v : values) {
      service.addGoodGenerator(new FixedByteArrayValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link ByteArrayValGenerationService} with bad {@link FixedByteArrayValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static ByteArrayValGenerationService byteArrayWithBad(Byte[] value, Byte[]... values) {
    ByteArrayValGenerationService service = new ByteArrayValGenerationService();
    service.addBadGenerator(new FixedByteArrayValGenerator(value));
    for (Byte[] v : values) {
      service.addBadGenerator(new FixedByteArrayValGenerator(v));
    }
    return service;
  }

}
