package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import net.minecraft.profiler.Profiler;

public class EntityAITasks {
	private static final Logger logger = LogManager.getLogger();
	private List<EntityAITaskEntry> taskEntries = Lists.<EntityAITaskEntry>newArrayList();
	private List<EntityAITaskEntry> executingTaskEntries = Lists.<EntityAITaskEntry>newArrayList();

	/** Instance of Profiler. */
	private final Profiler theProfiler;
	private int tickCount;
	private int tickRate = 3;

	public EntityAITasks(Profiler profilerIn) {
		this.theProfiler = profilerIn;
	}

	/**
	 * Add a now AITask. Args : priority, task
	 */
	public void addTask(int priority, EntityAIBase task) {
		this.taskEntries.add(new EntityAITaskEntry(priority, task));
	}

	/**
	 * removes the indicated task from the entity's AI tasks.
	 */
	public void removeTask(EntityAIBase task) {
		Iterator<EntityAITaskEntry> iterator = this.taskEntries.iterator();

		while (iterator.hasNext()) {
			EntityAITaskEntry entityaitasks$entityaitaskentry = (EntityAITaskEntry) iterator.next();
			EntityAIBase entityaibase = entityaitasks$entityaitaskentry.action;

			if (entityaibase == task) {
				if (this.executingTaskEntries.contains(entityaitasks$entityaitaskentry)) {
					entityaibase.resetTask();
					this.executingTaskEntries.remove(entityaitasks$entityaitaskentry);
				}

				iterator.remove();
			}
		}
	}

	public void onUpdateTasks() {
		this.theProfiler.startSection("goalSetup");

		if (this.tickCount++ % this.tickRate == 0) {
			Iterator iterator = this.taskEntries.iterator();
			label38:

			while (true) {
				EntityAITaskEntry entityaitasks$entityaitaskentry;

				while (true) {
					if (!iterator.hasNext()) {
						break label38;
					}

					entityaitasks$entityaitaskentry = (EntityAITaskEntry) iterator.next();
					boolean flag = this.executingTaskEntries.contains(entityaitasks$entityaitaskentry);

					if (!flag) {
						break;
					}

					if (!this.canUse(entityaitasks$entityaitaskentry) || !this.canContinue(entityaitasks$entityaitaskentry)) {
						entityaitasks$entityaitaskentry.action.resetTask();
						this.executingTaskEntries.remove(entityaitasks$entityaitaskentry);
						break;
					}
				}

				if (this.canUse(entityaitasks$entityaitaskentry) && entityaitasks$entityaitaskentry.action.shouldExecute()) {
					entityaitasks$entityaitaskentry.action.startExecuting();
					this.executingTaskEntries.add(entityaitasks$entityaitaskentry);
				}
			}
		} else {
			Iterator<EntityAITaskEntry> iterator1 = this.executingTaskEntries.iterator();

			while (iterator1.hasNext()) {
				EntityAITaskEntry entityaitasks$entityaitaskentry1 = (EntityAITaskEntry) iterator1.next();

				if (!this.canContinue(entityaitasks$entityaitaskentry1)) {
					entityaitasks$entityaitaskentry1.action.resetTask();
					iterator1.remove();
				}
			}
		}

		this.theProfiler.endSection();
		this.theProfiler.startSection("goalTick");

		for (EntityAITaskEntry entityaitasks$entityaitaskentry2 : this.executingTaskEntries) {
			entityaitasks$entityaitaskentry2.action.updateTask();
		}

		this.theProfiler.endSection();
	}

	/**
	 * Determine if a specific AI Task should continue being executed.
	 */
	private boolean canContinue(EntityAITaskEntry taskEntry) {
		boolean flag = taskEntry.action.continueExecuting();
		return flag;
	}

	/**
	 * Determine if a specific AI Task can be executed, which means that all running
	 * higher (= lower int value) priority tasks are compatible with it or all lower
	 * priority tasks can be interrupted.
	 */
	private boolean canUse(EntityAITaskEntry taskEntry) {
		for (EntityAITaskEntry entityaitasks$entityaitaskentry : this.taskEntries) {
			if (entityaitasks$entityaitaskentry != taskEntry) {
				if (taskEntry.priority >= entityaitasks$entityaitaskentry.priority) {
					if (!this.areTasksCompatible(taskEntry, entityaitasks$entityaitaskentry) && this.executingTaskEntries.contains(entityaitasks$entityaitaskentry)) {
						return false;
					}
				} else if (!entityaitasks$entityaitaskentry.action.isInterruptible() && this.executingTaskEntries.contains(entityaitasks$entityaitaskentry)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns whether two EntityAITaskEntries can be executed concurrently
	 */
	private boolean areTasksCompatible(EntityAITaskEntry taskEntry1, EntityAITaskEntry taskEntry2) {
		return (taskEntry1.action.getMutexBits() & taskEntry2.action.getMutexBits()) == 0;
	}

	class EntityAITaskEntry {
		public EntityAIBase action;
		public int priority;

		public EntityAITaskEntry(int priorityIn, EntityAIBase task) {
			this.priority = priorityIn;
			this.action = task;
		}
	}
}
