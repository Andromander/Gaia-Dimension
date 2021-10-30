function initializeCoreMod() {
    return {
        'attributes': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.world.level.levelgen.WorldGenSettings',
                'methodName': '<init>',
                'methodDesc': '(JZZLnet/minecraft/core/MappedRegistry;Ljava/util/Optional;)V'
            },
            'transformer': function (methodNode) {
                if (methodNode instanceof org.objectweb.asm.tree.MethodNode) {
                    var ASM = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
                    methodNode.instructions.insertBefore(
                        ASM.findFirstInstruction(methodNode, Opcodes.PUTFIELD),
                        ASM.listOf(
                            new org.objectweb.asm.tree.MethodInsnNode( // INVOKE androsa.gaiadimension.asm.ASMHooks#getSeed(long)
                                Opcodes.INVOKESTATIC,
                                'androsa/gaiadimension/asm/ASMHooks',
                                'getSeed',
                                '(J)J',
                                false
                            )
                        )
                    );
                }
                return methodNode;
            }
        }
    }
}