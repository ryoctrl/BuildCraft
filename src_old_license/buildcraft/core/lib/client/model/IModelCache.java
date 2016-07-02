package buildcraft.core.lib.client.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.vertex.VertexFormat;

import buildcraft.lib.client.model.MutableQuad;

public interface IModelCache<K> {
    void appendAsMutable(K key, List<MutableQuad> quads);

    ImmutableList<BakedQuad> bake(K key, VertexFormat format);

    void render(K key, VertexBuffer vb);

    void renderDisplayList(K key);
}
