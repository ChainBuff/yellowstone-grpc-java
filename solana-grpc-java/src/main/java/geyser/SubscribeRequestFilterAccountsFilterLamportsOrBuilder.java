// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: geyser.proto

package geyser;

public interface SubscribeRequestFilterAccountsFilterLamportsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:geyser.SubscribeRequestFilterAccountsFilterLamports)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 eq = 1;</code>
   * @return Whether the eq field is set.
   */
  boolean hasEq();
  /**
   * <code>uint64 eq = 1;</code>
   * @return The eq.
   */
  long getEq();

  /**
   * <code>uint64 ne = 2;</code>
   * @return Whether the ne field is set.
   */
  boolean hasNe();
  /**
   * <code>uint64 ne = 2;</code>
   * @return The ne.
   */
  long getNe();

  /**
   * <code>uint64 lt = 3;</code>
   * @return Whether the lt field is set.
   */
  boolean hasLt();
  /**
   * <code>uint64 lt = 3;</code>
   * @return The lt.
   */
  long getLt();

  /**
   * <code>uint64 gt = 4;</code>
   * @return Whether the gt field is set.
   */
  boolean hasGt();
  /**
   * <code>uint64 gt = 4;</code>
   * @return The gt.
   */
  long getGt();

  public SubscribeRequestFilterAccountsFilterLamports.CmpCase getCmpCase();
}
